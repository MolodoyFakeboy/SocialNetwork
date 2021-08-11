package com.social.network.Dao;

import com.social.network.Model.Chat;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.util.List;

public class ChatDao  extends AbstractGenericDaoImpl<Chat> {

    public ChatDao() {
        setType(Chat.class);
    }

    @Override
    public void add(Chat enity) {
        super.add(enity);
    }

    @Override
    public Chat update(Chat enity) {
        return super.update(enity);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public List<Chat> findAll() {
        return super.findAll();
    }

    @Override
    public Chat find(int id) {
        return super.find(id);
    }

    @Override
    public void setType(Class<Chat> type) {
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
