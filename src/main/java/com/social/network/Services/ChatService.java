package com.social.network.Services;

import com.social.network.Dao.GenericDao;
import com.social.network.Dto.MessageDTO;
import com.social.network.Facade.MessageFacade;
import com.social.network.Model.Chat;
import com.social.network.Model.Message;
import com.social.network.Model.User;
import com.social.network.Services.Interfaces.IChatSerivce;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private GenericDao<User> userDao;

    private MessageFacade messageFacade;

    @Autowired
    public ChatService(GenericDao<Chat> chatDao, GenericDao<User> userDao) {
        this.chatDao = chatDao;
        this.userDao = userDao;
        log = LogManager.getLogger(ImageService.class);
    }

    @Autowired
    public void setMessageFacade(MessageFacade messageFacade) {
        this.messageFacade = messageFacade;
    }

    @Override
    public Chat startNewChat(Principal principal, int userID) {
        Chat chat = null;
        try {
            User user = findByPrincipal(principal.getName());
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

    private User findByPrincipal(String name) {
        User timeUser = null;
        try {
            EntityManager em = chatDao.getEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<User> query = cb.createQuery(User.class);
            Root<User> user = query.from(User.class);
            Predicate userPredicate = cb.equal(user.get("username"), name);
            query.select(user).where(userPredicate);
            timeUser = em.createQuery(query).setMaxResults(1).getSingleResult();
        } catch (Exception e) {
            log.error("Cannot find User with this name");
        }
        return timeUser;
    }

    @Override
    public List<MessageDTO> getMessagesInChat(int chatId, Principal principal) {
        Chat chat = chatDao.find(chatId);
        if (chat != null) {
            if (chat.getUsers().contains(findByPrincipal(principal.getName()))) {
                List<Message> messages = new ArrayList<>(chat.getMessages());
                return messages.stream().map(messageFacade::messageToDto).
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
