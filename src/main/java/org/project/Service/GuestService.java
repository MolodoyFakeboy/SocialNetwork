package org.project.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.project.Dao.GenericDao;
import org.project.Model.Guest;
import org.project.Model.Room;
import org.project.Model.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;


@org.springframework.stereotype.Service
@Transactional
public class GuestService implements IGuestService {

    private GenericDao <Guest> genericDao;

    private Logger log;

    @Autowired
    public GuestService(GenericDao<Guest> genericDao) {
        this.genericDao = genericDao;
        log = LogManager.getLogger(GuestService.class);
    }

    @Override
    public Guest addGuest(Guest guest) {
        genericDao.add(guest);
        return guest;
    }

    @Override
    public Room bookRoom(int guestID,Room room) {
        try {
            Guest guest = genericDao.find(guestID);
            guest.getRooms().add(room);
            genericDao.update(guest);
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }
        return room;
    }

    @Override
    public Boolean leaveHotel(Room room, int id) {
        try {
            Guest guest = genericDao.find(id);
            guest.getRooms().remove(room);
            guest.getLastRooms().add(room);
            genericDao.update(guest);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return true;
    }

    @Override
    public Service useService(Guest guest, Service service) {
        try {
            guest.getServices().add(service);
            service.setDate(new Timestamp(System.currentTimeMillis()));
            genericDao.update(guest);
            log.info(" Гость воспользовался услугой " + service.getName());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return service;
    }

    @Override
    public double getaBill(int guestIndex) {
        double bill = 0;
        List<Room> list = null;
        EntityManager em = genericDao.getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Room> query = cb.createQuery(Room.class);
            Root<Room> rooms = query.from(Room.class);
            Join<Guest, Room> guestRoomJoin = rooms.join("guests");
            Predicate guestPredicate = cb.equal(guestRoomJoin.get("id"), guestIndex);
            query.select(rooms).where(guestPredicate);
            list = em.createQuery(query).getResultList();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        for (Room room : list){
            bill =+ room.getBasePrice();
        }
        return  bill;
    }

    @Override
    public List<Guest> getNumberGuest() {
        List<Guest> list = null;
        EntityManager em = genericDao.getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Guest> query = cb.createQuery(Guest.class);
            Root<Guest> guests = query.from(Guest.class);
            Join<Guest, Room> guestRoomJoin = guests.join("rooms");
            query.select(guests).where(guestRoomJoin.get("roomId").isNotNull());
            list = em.createQuery(query).getResultList();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return list;
    }

    @Override
    public List<Service> sortUsingServicePrice(int guestIndex) {
        List<Service> list = null;
        EntityManager em = genericDao.getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Service> query = cb.createQuery(Service.class);
            Root<Service> services = query.from(Service.class);
            Join<Guest, Service> guestServiceJoin = services.join("guests");
            Predicate guestPredicate = cb.equal(guestServiceJoin.get("id"), guestIndex);
            query.select(services).where(guestPredicate);
            query.orderBy(cb.asc(services.get("price")));
            list = em.createQuery(query).getResultList();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return list;

    }

    @Override
    public List<Service> sortUsingServiceTime(int guestIndex) {
        List<Service> list = null;
        EntityManager em = genericDao.getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Service> query = cb.createQuery(Service.class);
            Root<Service> services = query.from(Service.class);
            Join<Guest, Service> guestServiceJoin = services.join("guests");
            Predicate guestPredicate = cb.equal(guestServiceJoin.get("id"), guestIndex);
            query.select(services).where(guestPredicate);
            query.orderBy(cb.asc(services.get("date")));
            list = em.createQuery(query).getResultList();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return list;
    }

    @Override
    public Guest getGuest(int index) {
        return genericDao.find(index);
    }
}
