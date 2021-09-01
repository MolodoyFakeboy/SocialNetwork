package com.social.network.dao;

import com.social.network.model.Image;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ImageDao extends AbstractGenericDaoImpl<Image> {

    public ImageDao() {
        setType(Image.class);
    }

    @Override
    public void add(Image enity) {
        super.add(enity);
    }

    @Override
    public Image update(Image enity) {
        return super.update(enity);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public List<Image> findAll() {
        return super.findAll();
    }

    @Override
    public Image find(int id) {
        return super.find(id);
    }

    @Override
    public void setType(Class<Image> type) {
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
