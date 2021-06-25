package org.project.Dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.project.Util.JPAUtility;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.util.List;

public abstract class HibernateAbstractDao<T extends Serializable> implements GenericDao<T> {

    private Class<T> type;

    private EntityManager entityManager;

    private  final Logger log = LogManager.getLogger(HibernateAbstractDao.class);

    @Override
    public void add(T enity) {
        entityManager = getEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            entityManager.persist(enity);
            tx.commit();
        } catch (Exception e) {
            log.error(e.getMessage());
            tx.rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public T update(T enity) {
        entityManager = getEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            entityManager.detach(enity);
            entityManager.merge(enity);
            tx.commit();
        } catch (Exception e) {
            log.error(e.getMessage());
            tx.rollback();
        }finally {
            entityManager.close();
        }
        return enity;
    }

    @Override
    public void delete(int id) {
        entityManager = getEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            Object object = entityManager.find(type, id);
            entityManager.remove(object);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            log.error(e.getMessage());
        } finally {
            entityManager.close();
        }

    }

    @Override
    public List<T> findAll() {
        return getEntityManager().createQuery("from" + type.getName()).getResultList();
    }

    @Override
    public T find(int id) {
        entityManager = getEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        T t = null;
        try {
            tx.begin();
             t = entityManager.find(type, id);
            entityManager.detach(t);
        } catch (Exception e) {
            log.error(e.getMessage());
            tx.rollback();
        } finally {
            entityManager.close();
        }
        return t;
    }

    public void setType(Class<T> type) {
        this.type = type;
    }

    protected EntityManager getEntityManager() {
        return JPAUtility.getEntityManager();
    }
}
