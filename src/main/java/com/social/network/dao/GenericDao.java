package com.social.network.dao;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public interface GenericDao<T> extends Serializable {

    void add(T enity);

    T update(T enity);

    void delete(int id);

    List<T> findAll();

    T find(int id);

    EntityManager getEntityManager();

}
