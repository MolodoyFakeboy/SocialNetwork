package com.social.network.dao;

import com.social.network.model.Message;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class MessageDao extends AbstractGenericDaoImpl<Message> {

    public MessageDao() {
        setType(Message.class);
    }

    @Override
    public void add(Message enity) {
        super.add(enity);
    }

    @Override
    public Message update(Message enity) {
        return super.update(enity);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public List<Message> findAll() {
        return super.findAll();
    }

    @Override
    public Message find(int id) {
        return super.find(id);
    }

    @Override
    public void setType(Class<Message> type) {
        super.setType(type);
    }

    @Override
    protected Session getSession() {
        return super.getSession();
    }

    @Override
    public EntityManager getEntityManager() {
        return super.getEntityManager();
    }
}
