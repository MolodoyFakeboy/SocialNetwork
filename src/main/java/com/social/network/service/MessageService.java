package com.social.network.service;

import com.social.network.dao.GenericDao;
import com.social.network.dao.IUserDao;
import com.social.network.dto.MessageDTO;
import com.social.network.model.Chat;
import com.social.network.model.Message;
import com.social.network.model.User;
import com.social.network.service.interfac.IMessageService;
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
