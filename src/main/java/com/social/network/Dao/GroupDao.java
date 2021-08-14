package com.social.network.Dao;

import com.social.network.Model.Group;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class GroupDao extends AbstractGenericDaoImpl<Group> {

    public GroupDao() {
       setType(Group.class);
    }

    @Override
    public void add(Group enity) {
        super.add(enity);
    }

    @Override
    public Group update(Group enity) {
        return super.update(enity);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public List<Group> findAll() {
        return super.findAll();
    }

    @Override
    public Group find(int id) {
        return super.find(id);
    }

    @Override
    public void setType(Class<Group> type) {
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
