package org.project.Dao;

import org.hibernate.Session;
import org.project.Model.Guest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class GuestDao extends HibernateAbstractDao<Guest> {

    public GuestDao() {
        setType(Guest.class);
    }

    @Override
    public void add(Guest enity) {
        super.add(enity);
    }

    @Override
    public Guest update(Guest enity) {
        return super.update(enity);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public List<Guest> findAll() {
        return super.findAll();
    }

    @Override
    public Guest find(int id) {
        return super.find(id);
    }

    @Override
    public void setType(Class<Guest> type) {
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