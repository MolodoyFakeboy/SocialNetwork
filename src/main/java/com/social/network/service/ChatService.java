package com.social.network.service;

import com.social.network.dao.GenericDao;
import com.social.network.dao.IUserDao;
import com.social.network.dto.MessageDTO;
import com.social.network.mapper.MessageMapper;
import com.social.network.model.Chat;
import com.social.network.model.Message;
import com.social.network.model.User;
import com.social.network.service.interfac.IChatSerivce;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ChatService implements IChatSerivce {

    private Logger log;

    private GenericDao<Chat> chatDao;

    private IUserDao userDao;

    private MessageMapper messageMapper = Mappers.getMapper(MessageMapper.class);

    @Autowired
    public ChatService(GenericDao<Chat> chatDao, IUserDao userDao) {
        this.chatDao = chatDao;
        this.userDao = userDao;
        log = LogManager.getLogger(ImageService.class);
    }

    @Override
    public Chat startNewChat(Principal principal, int userID) {
        Chat chat = null;
        try {
            User user = userDao.findByName(principal.getName());
            User user2 = userDao.find(userID);
            chat = new Chat(user.getUsername() + " + " + user2.getUsername());
            user.getChats().add(chat);
            user2.getChats().add(chat);
            chatDao.add(chat);
            log.info("Chat was created with " + user.getUsername() + user2.getUsername());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return chat;
    }

    @Override
    public List<MessageDTO> getMessagesInChat(int chatId, Principal principal) {
        Chat chat = chatDao.find(chatId);
        if (chat != null) {
            if (chat.getUsers().contains(userDao.findByName(principal.getName()))) {
                List<Message> messages = new ArrayList<>(chat.getMessages());
                return messages.stream().map(messageMapper::messageToMessageDto).
                        sorted(Comparator.comparing(MessageDTO::getSendTime))
                        .collect(Collectors.toList());
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public List<Chat> getAllUserChats(Principal principal) {
        List<Chat> userChats = null;
        try {
            EntityManager em = chatDao.getEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Chat> query = cb.createQuery(Chat.class);
            Root<Chat> chats = query.from(Chat.class);
            Join<User, Chat> chatsJoin = chats.join("users");
            Predicate userPredicate = cb.equal(chatsJoin.get("username"), principal.getName());
            query.select(chats).where(userPredicate);
            userChats = em.createQuery(query).getResultList();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return userChats;
    }

}
