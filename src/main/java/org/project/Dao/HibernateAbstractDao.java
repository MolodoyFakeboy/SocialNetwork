package org.project.Dao;

import org.project.Util.JPAUtility;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public abstract class HibernateAbstractDao<T extends Serializable> implements GenericDao<T> {

    private Class<T> type;

    EntityManager entityManager;

    @Override
    public void add(T enity) {
        entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(enity);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public T update(T enity) {
        entityManager = getEntityManager();
        entityManager.detach(enity);
        entityManager.getTransaction().begin();
        entityManager.merge(enity);
        entityManager.getTransaction().commit();
        entityManager.close();
        return enity;
    }

    @Override
    public void delete(int id) {
        entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        Object object = entityManager.find(type, id);
        entityManager.remove(object);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<T> findAll() {
        return getEntityManager().createQuery("from" + type.getName()).getResultList();
    }

    @Override
    public T find(int id) {
        entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        T t = entityManager.find(type, id);
        entityManager.detach(t);
        entityManager.close();
        return t;
    }


    public void setType(Class<T> type) {
        this.type = type;
    }

    protected EntityManager getEntityManager() {
        return JPAUtility.getEntityManager();
    }
}
