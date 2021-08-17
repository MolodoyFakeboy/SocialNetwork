package com.social.network.Dao;

import com.social.network.Model.Publication;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PublicationDao extends AbstractGenericDaoImpl <Publication> {

    public PublicationDao() {
        setType(Publication.class);
    }

    @Override
    public void add(Publication enity) {
        super.add(enity);
    }

    @Override
    public Publication update(Publication enity) {
        return super.update(enity);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public List<Publication> findAll() {
        return super.findAll();
    }

    @Override
    public Publication find(int id) {
        return super.find(id);
    }

    @Override
    public void setType(Class<Publication> type) {
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
