package com.social.network.dao;

import com.social.network.model.Post;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PostDao extends AbstractGenericDaoImpl<Post> {

    public PostDao() {
       setType(Post.class);
    }

    @Override
    public void add(Post enity) {
        super.add(enity);
    }

    @Override
    public Post update(Post enity) {
        return super.update(enity);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public List<Post> findAll() {
        return super.findAll();
    }

    @Override
    public Post find(int id) {
        return super.find(id);
    }

    @Override
    public void setType(Class<Post> type) {
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
