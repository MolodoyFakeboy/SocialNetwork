package org.project.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.project.Dao.GenericDao;
import org.project.Model.EnumStatus;
import org.project.Model.Guest;
import org.project.Model.Room;
import org.project.Util.JPAUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


@Service
public class RoomService implements IRoomService {

    private GenericDao<Room> genericDao;

    private Logger log;

    @Value("${my.boolean.status}")
    private Boolean status;

    @Value("${my.boolean.history}")
    private Boolean history;


    @Autowired
    public RoomService(GenericDao<Room> genericDao) {
        this.genericDao = genericDao;
        log = LogManager.getLogger(RoomService.class);
    }

    @Override
    public Room addNewRoom(Room room) {
        genericDao.add(room);
        log.info("Комната добавлена");
        return room;
    }


    @Override
    public Boolean removeRoom(int id) {
        genericDao.delete(id);
        log.info("Комната удалена");
        return true;
    }


    @Override
    public Room changeRoomStatus(Room room, EnumStatus roomStatus) {
        try {
            if (status) {
                room.setStatus(roomStatus);
                genericDao.update(room);
                log.info(room.getStatus());
                return room;
            } else {
                log.info("Сейчас нельзя выполнить это действие");
            }
        } catch (Exception e) {
            log.error("Не удалось выполнить действие");
        }
        return room;
    }

    @Override
    public Room changePriceonRoom(Room room, double price) {
        room.setBasePrice(price);
        genericDao.update(room);
        log.info(room.getBasePrice());
        return room;
    }

    @Override
    public List<Room> sortRoomforPrice() {
        EntityManager em = JPAUtility.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Room> query = cb.createQuery(Room.class);
        Root<Room> root = query.from(Room.class);
        query.select(root);
        query.orderBy(cb.asc(root.get("basePrice")));
        List<Room> list = em.createQuery(query).getResultList();
        Stream<Room> stream = list.stream();
        stream.forEach(System.out::println);
        return list;
    }

    @Override
    public List<Room> sortRoomforBed() {
        EntityManager em = JPAUtility.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Room> query = cb.createQuery(Room.class);
        Root<Room> root = query.from(Room.class);
        query.select(root);
        query.orderBy(cb.asc(root.get("numBed")));
        List<Room> list = em.createQuery(query).getResultList();
        Stream<Room> stream = list.stream();
        stream.forEach(log::info);
        return list;
    }

    @Override
    public List<Room> sortRoomforStars() {
        EntityManager em = JPAUtility.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Room> query = cb.createQuery(Room.class);
        Root<Room> root = query.from(Room.class);
        query.select(root).orderBy(cb.asc(root.get("numberOfStars")));
        List<Room> list = em.createQuery(query).getResultList();
        Stream<Room> stream = list.stream();
        stream.forEach(System.out::println);
        return list;
    }

    @Override
    public List<Room> sortFreeRoomforPrice() {
        EntityManager em = JPAUtility.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Room> query = cb.createQuery(Room.class);
        Root<Room> room = query.from(Room.class);
        Join<Room, Guest> roomGuestJoin = room.join("guests", JoinType.LEFT);
        query.select(room).where(roomGuestJoin.get("id").isNull()).orderBy(cb.asc(room.get("basePrice")));
        List<Room> list = em.createQuery(query).getResultList();
        Stream<Room> stream = list.stream();
        stream.forEach(System.out::println);
        return list;
    }

    @Override
    public List<Room> sortFreeRoomBed() {
        EntityManager em = JPAUtility.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Room> query = cb.createQuery(Room.class);
        Root<Room> room = query.from(Room.class);
        Join<Room, Guest> roomGuestJoin = room.join("guests", JoinType.LEFT);
        query.select(room).where(roomGuestJoin.get("id").isNull()).orderBy(cb.asc(room.get("numBed")));
        List<Room> list = em.createQuery(query).getResultList();
        Stream<Room> stream = list.stream();
        stream.forEach(log::info);
        return list;
    }

    @Override
    public List<Room> sortFreeRoomStars() {
        EntityManager em = JPAUtility.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Room> query = cb.createQuery(Room.class);
        Root<Room> room = query.from(Room.class);
        Join<Room, Guest> roomGuestJoin = room.join("guests", JoinType.LEFT);
        query.select(room).where(roomGuestJoin.get("id").isNull()).orderBy(cb.asc(room.get("numberOfStars")));
        List<Room> list = em.createQuery(query).getResultList();
        Stream<Room> stream = list.stream();
        stream.forEach(log::info);
        return list;
    }

    @Override
    public List<Room> getAmountFreeRoom() {
        EntityManager em = JPAUtility.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Room> query = cb.createQuery(Room.class);
        Root<Room> room = query.from(Room.class);
        Join<Room, Guest> roomGuestJoin = room.join("guests", JoinType.LEFT);
        query.select(room).where(roomGuestJoin.get("id").isNull());
        List<Room> list = em.createQuery(query).getResultList();
        log.info(" Колличество свободных комнат: " + list.size());
        return list;
    }

    @Override
    public List<Room> sortRoomIsFree(Date date) throws ParseException {
        EntityManager em = JPAUtility.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();

        //Запрос номер 1
        CriteriaQuery<Room> query = cb.createQuery(Room.class);
        Root<Room> room = query.from(Room.class);
        Join<Room, Guest> roomGuestJoin = room.join("guests", JoinType.LEFT);
        query.select(room).where(roomGuestJoin.get("id").isNull());
        List<Room> list = em.createQuery(query).getResultList();

        //Запрос номер 2
        CriteriaQuery<Room> query1 = cb.createQuery(Room.class);
        Root<Room> room1 = query1.from(Room.class);
        Join<Room, Guest> roomGuestJoin1 = room1.join("guests", JoinType.LEFT);
        Predicate localDatePredicate = cb.lessThan(roomGuestJoin1.get("localDate"), date);
        query1.select(room1).where(localDatePredicate);
        List<Room> list1 = em.createQuery(query1).getResultList();

        //union
        list.addAll(list1);
        Stream<Room> stream = list.stream();
        stream.forEach(System.out::println);
        return list;
    }

    @Override
    public List<Guest> getLastThreeGuest(int index) {
        List<Guest> list = new ArrayList<>();
        try {
            if (history) {
                EntityManager em = JPAUtility.getEntityManager();
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<Guest> query = cb.createQuery(Guest.class);
                Root<Guest> guests = query.from(Guest.class);
                Join<Guest, Room> guestRoomJoin = guests.join("lastRooms", JoinType.LEFT);
                Predicate guestPredicate = cb.equal(guestRoomJoin.get("roomId"), index);
                query.select(guests).where(guestPredicate);
                list = em.createQuery(query).setMaxResults(3).getResultList();
                Stream<Guest> stream = list.stream();
                stream.forEach(System.out::println);
            } else {
                System.out.println("Пока нельзя выполнить это действие измините настройки property");
            }
        } catch (Exception e) {
            System.out.println("Ошибка в поиске 3 последних гостей");
        }
        return list;
    }


    @Override
    public Room getInfoRoom(int index) {
        Room room = (Room) genericDao.find(index);
        log.info(" Комнатан находится " + room.getFloor() + " этаже ");
        log.info(" Количество кроватей в комнате " + room.getNumBed());
        log.info(" Стоиость комнаты составит " + room.getBasePrice());
        log.info("Количество звезд у комнаты: " + room.getNumberOfStars());
        try {
            if (room.getGuests().isEmpty()) {
                log.info("В комнате не проживают гости");
            } else {
                log.info("В данный момент комната занята");
            }
        } catch (Exception exception) {
            log.error("Нет гостей");
        }
        return room;
    }

    @Override
    public Room getRoom(int index) {
        return genericDao.find(index);
    }

}
