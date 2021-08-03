package org.project.Dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

public abstract class HibernateAbstractDao<T extends Serializable> implements GenericDao<T> {

    private Class<T> type;

    @PersistenceContext
    private EntityManager entityManager;

    private  final Logger log = LogManager.getLogger(HibernateAbstractDao.class);

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
        return getSession().createQuery("from" + type.getName()).getResultList();
    }

    @Override
    public T find(int id) {
        T t = getSession().find(type,id);
        return t;
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
