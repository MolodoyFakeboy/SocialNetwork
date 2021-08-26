package com.social.network.Dao;

import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

public class AbstractGenericDaoImpl <T extends Serializable> implements GenericDao<T>{

    private Class<T> type;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(T enity) {
        getSession().persist(enity);
    }

    @Override
    public T update(T enity) {
        getSession().update(enity);
        return enity;
    }

    @Override
    public void delete(int id) {
        Object object = getSession().find(type,id);
        getSession().delete(object);
    }

    @Override
    public List<T> findAll() {
        return getSession().createQuery("from " + type.getName()).getResultList();
    }

    @Override
    public T find(int id) {
        return getSession().find(type,id);
    }

    public void setType(Class<T> type) {
        this.type = type;
    }

    @Transactional
    protected Session getSession(){
        return  entityManager.unwrap(Session.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

}
