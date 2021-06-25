package org.project.Dao;

import org.project.Annotations.Singleton;
import org.project.Model.Room;

import javax.persistence.EntityManager;
import java.util.List;

@Singleton
public class RoomDao extends HibernateAbstractDao <Room> {

    public RoomDao() {
        setType(Room.class);
    }

    @Override
    public void add(Room enity) {
        super.add(enity);
    }

    @Override
    public Room update(Room enity) {
        return super.update(enity);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public List<Room> findAll() {
        return super.findAll();
    }

    @Override
    public Room find(int id) {
        return super.find(id);
    }

    @Override
    public void setType(Class<Room> type) {
        super.setType(type);
    }

    @Override
    protected EntityManager getEntityManager() {
        return super.getEntityManager();
    }
}
