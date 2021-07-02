package org.project.Dao;

import org.project.Model.Service;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ServiceDao extends HibernateAbstractDao <Service> {

    public ServiceDao(){
        setType(Service.class);
    }

    @Override
    public void add(Service enity) {
        super.add(enity);
    }

    @Override
    public Service update(Service enity) {
        return super.update(enity);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public List<Service> findAll() {
        return super.findAll();
    }

    @Override
    public Service find(int id) {
        return super.find(id);
    }

    @Override
    public void setType(Class<Service> type) {
        super.setType(type);
    }

    @Override
    protected EntityManager getEntityManager() {
        return super.getEntityManager();
    }
}

