package org.project.Dao;

import org.project.Annotations.Singleton;
import org.project.Model.Guest;

import javax.persistence.EntityManager;
import java.util.List;

@Singleton
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
    protected EntityManager getEntityManager() {
        return super.getEntityManager();
    }
}