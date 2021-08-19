package com.social.network.Services;

import com.social.network.Dao.GenericDao;
import com.social.network.Model.Chat;
import com.social.network.Model.Message;
import com.social.network.Model.User;
import com.social.network.Services.Interfaces.IMessageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.security.Principal;

@Transactional
@Service
public class MessageService implements IMessageService {

    private Logger log;

    private GenericDao<Message> messageGenericDao;

    private GenericDao<Chat> chatDao;

    @Autowired
    public MessageService(GenericDao<Message> messageGenericDao, GenericDao<Chat> chatDao) {
        this.messageGenericDao = messageGenericDao;
        this.chatDao = chatDao;
        log = LogManager.getLogger(MessageService.class);
    }

    @Override
    public Message writeMessageToUser(Message message, Principal principal, int chatID) {
        User user = findByPrincipal(principal.getName());
        Chat chat = chatDao.find(chatID);
        message.setUser(user);
        message.setChat(chat);
        messageGenericDao.add(message);
        return message;
    }

    private User findByPrincipal(String name) {
        User timeUser = null;
        try {
            EntityManager em = messageGenericDao.getEntityManager();
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
}
