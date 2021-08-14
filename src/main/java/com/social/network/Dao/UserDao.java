package com.social.network.Dao;

import com.social.network.Model.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDao extends AbstractGenericDaoImpl<User> {

    public UserDao() {
        setType(User.class);
    }

    @Override
    public void add(User enity) {
        super.add(enity);
    }

    @Override
    public User update(User enity) {
        return super.update(enity);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public List<User> findAll() {
        return super.findAll();
    }

    @Override
    public User find(int id) {
        return super.find(id);
    }

    @Override
    public void setType(Class<User> type) {
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
