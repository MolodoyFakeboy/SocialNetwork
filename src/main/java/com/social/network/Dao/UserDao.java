package com.social.network.Dao;

import com.social.network.Model.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserDao extends AbstractGenericDaoImpl<User> implements IUserDao {

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

    @Override
    public User findByName(String name) {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> user = query.from(User.class);
        Predicate userPredicate = cb.equal(user.get("username"), name);
        query.select(user).where(userPredicate);
        return em.createQuery(query).setMaxResults(1).getSingleResult();
    }
}
