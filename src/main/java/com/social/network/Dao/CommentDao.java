package com.social.network.Dao;

import com.social.network.Model.Comment;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CommentDao extends AbstractGenericDaoImpl<Comment>{

    public CommentDao() {
        setType(Comment.class);
    }

    @Override
    public void add(Comment enity) {
        super.add(enity);
    }

    @Override
    public Comment update(Comment enity) {
        return super.update(enity);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public List<Comment> findAll() {
        return super.findAll();
    }

    @Override
    public Comment find(int id) {
        return super.find(id);
    }

    @Override
    public void setType(Class<Comment> type) {
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
