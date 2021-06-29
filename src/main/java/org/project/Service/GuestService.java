package org.project.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.project.Dao.GenericDao;
import org.project.Model.Guest;
import org.project.Model.Room;
import org.project.Model.Service;
import org.project.Util.JPAUtility;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Stream;


@org.springframework.stereotype.Service
public class GuestService implements IGuestService {

    private GenericDao <Guest> genericDao;

    private Logger log;

    @Autowired
    public GuestService(GenericDao<Guest> genericDao) {
        this.genericDao = genericDao;
        log = LogManager.getLogger(GuestService.class);
    }

    @Override
    public Room bookRoom(Room room, Guest guest) {
        try {
            genericDao.add(guest);
            guest.getRooms().add(room);
            genericDao.update(guest);
        } catch (Exception exception) {
            exception.printStackTrace();
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
            log.info("Приезжайте к нам еще");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public Service useService(Guest guest, Service service) {
        guest.getServices().add(service);
        service.setDate(new Timestamp(System.currentTimeMillis()));
        genericDao.update(guest);
        log.info(" Гость воспользовался услугой " + service.getName());
        return service;
    }

    @Override
    public void getaBill(int guestIndex) {
        EntityManager em = JPAUtility.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Room> query = cb.createQuery(Room.class);
        Root<Room> rooms = query.from(Room.class);
        Join<Guest, Room> guestRoomJoin = rooms.join("guests");
        Predicate guestPredicate = cb.equal(guestRoomJoin.get("id"), guestIndex);
        query.select(rooms).where(guestPredicate);
        List<Room> list =  em.createQuery(query).getResultList();
        Stream<Room> stream = list.stream();
        stream.forEach(room -> System.out.println("Сумма к оплате за номер " + room.getBasePrice()));
    }

    @Override
    public List<Guest> getNumberGuest() {
        EntityManager em = JPAUtility.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Guest> query = cb.createQuery(Guest.class);
        Root<Guest> guests = query.from(Guest.class);
        Join<Guest, Room> guestRoomJoin = guests.join("rooms");
        query.select(guests).where(guestRoomJoin.get("roomId").isNotNull());
        List<Guest> list = em.createQuery(query).getResultList();
        log.info(" Колличество гостей в отеле: " + list.size());
        return list;
    }

    @Override
    public List<Service> sortUsingServicePrice(int guestIndex) {
        EntityManager em = JPAUtility.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Service> query = cb.createQuery(Service.class);
        Root<Service> services = query.from(Service.class);
        Join<Guest, Service> guestServiceJoin = services.join("guests");
        Predicate guestPredicate = cb.equal(guestServiceJoin.get("id"), guestIndex);
        query.select(services).where(guestPredicate);
        query.orderBy(cb.asc(services.get("price")));
        List<Service> list = em.createQuery(query).getResultList();
        Stream<Service> stream = list.stream();
        stream.forEach(System.out::println);
        return list;

    }

    @Override
    public List<Service> sortUsingServiceTime(int guestIndex) {
        EntityManager em = JPAUtility.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Service> query = cb.createQuery(Service.class);
        Root<Service> services = query.from(Service.class);
        Join<Guest, Service> guestServiceJoin = services.join("guests");
        Predicate guestPredicate = cb.equal(guestServiceJoin.get("id"), guestIndex);
        query.select(services).where(guestPredicate);
        query.orderBy(cb.asc(services.get("date")));
        List<Service> list = em.createQuery(query).getResultList();
        Stream<Service> stream = list.stream();
        stream.forEach(log::info);
        return list;
    }

    @Override
    public Guest getGuest(int index) {
        return (Guest) genericDao.find(index);
    }




}
