package org.project.Dao;

import org.hibernate.Session;

import org.project.Model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDao extends HibernateAbstractDao<User> {

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
