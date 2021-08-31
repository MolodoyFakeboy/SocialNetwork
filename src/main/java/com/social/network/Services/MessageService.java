package com.social.network.Services;

import com.social.network.Dao.GenericDao;
import com.social.network.Dao.IUserDao;
import com.social.network.Dto.MessageDTO;
import com.social.network.Model.Chat;
import com.social.network.Model.Message;
import com.social.network.Model.User;
import com.social.network.Services.Interfaces.IMessageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Transactional
@Service
public class MessageService implements IMessageService {

    private Logger log;

    private GenericDao<Message> messageGenericDao;

    private GenericDao<Chat> chatDao;

    private IUserDao userDao;

    @Autowired
    public MessageService(GenericDao<Message> messageGenericDao, GenericDao<Chat> chatDao, IUserDao userDao) {
        this.messageGenericDao = messageGenericDao;
        this.chatDao = chatDao;
        this.userDao = userDao;
        log = LogManager.getLogger(MessageService.class);
    }

    @Override
    public Message writeMessageToUser(MessageDTO messageFromRequest, Principal principal, int chatID) {
        User user = userDao.findByName(principal.getName());
        Chat chat = chatDao.find(chatID);
        Message message = new Message(messageFromRequest.getSendText());
        try {
            message.setUser(user);
            message.setChat(chat);
            messageGenericDao.add(message);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return message;
    }

}
