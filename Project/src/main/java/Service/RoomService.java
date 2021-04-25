package src.main.java.Service;


import src.main.java.Annotations.InjectByType;
import src.main.java.Annotations.Singleton;
import src.main.java.Dao.IRoomDao;
import src.main.java.Model.EnumStatus;
import src.main.java.Model.Guest;
import src.main.java.Model.Room;

import src.main.java.Util.Prop;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;


@Singleton
public class RoomService implements IRoomService {


    @InjectByType
    private IRoomDao roomDao;

    private  Logger log;

    public RoomService(IRoomDao roomDao)
    {
        this.roomDao = roomDao;
        log = Logger.getLogger(RoomService.class);
    }


    @Override
    public Room addNewRoom(Room room) {
        roomDao.addRoom(room);
        return room;
    }


    @Override
    public Boolean removeRoom(Room room) {
        roomDao.removeRoom(room);
        log.info("Комната удалена");
        return true;
    }


    @Override
    public Room changeRoomStatus(Room room, EnumStatus status) {
        try {
            if(Prop.getProperties().getBoolean("status", true)){
                room.setStatus(status);
                System.out.println(room.getStatus());
                return room;
            } else {
                log.info("Сейчас нельзя выполнить это действие");
            }
        } catch (Exception e){
            log.error("Не удалось выполнить действие");
        }
        return room;
    }

    @Override
    public Room changePriceonRoom(Room room, double price) {
        room.setBasePrice(price);
        log.info(room.getBasePrice());
        return room;
    }

    @Override
    public Stream<Room> sortRoomforPrice() {
        Stream<Room> stream = roomDao.getRooms().stream();
        stream.sorted(Comparator.comparing(Room::getBasePrice)).forEach(log::info);
        return stream;
    }

    @Override
    public Stream<Room> sortRoomforBed() {
        Stream<Room> stream = roomDao.getRooms().stream();
        stream.sorted(Comparator.comparing(Room::getNumBed)).forEach(log::info);
        return stream;
    }

    @Override
    public Stream<Room> sortRoomforStars() {
        Stream<Room> streamFromCollection = roomDao.getRooms().stream();
        streamFromCollection.sorted(Comparator.comparing(Room::getNumberOfStars)).forEach(log::info);
        return streamFromCollection;

    }

    @Override
    public Stream<Room> sortFreeRoomforPrice() {
        Stream<Room> stream = roomDao.getRooms().stream();
        stream.sorted(Comparator.comparing(Room::getBasePrice)).filter(room -> room.getGuests().isEmpty()).forEach(log::info);
        return  stream;
    }

    @Override
    public Stream<Room> sortFreeRoomBed() {
        Stream<Room> stream = roomDao.getRooms().stream();
        stream.sorted(Comparator.comparing(Room::getNumBed)).filter(room -> room.getGuests().isEmpty()).forEach(log::info);
        return  stream;
    }

    @Override
    public Stream<Room> sortFreeRoomStars()  {
        Stream<Room> stream = roomDao.getRooms().stream();
        stream.sorted(Comparator.comparing(Room::getNumberOfStars)).filter(room -> room.getGuests().isEmpty()).forEach(log::info);
        return  stream;
    }

    @Override
    public ArrayList<Room> getAmountFreeRoom()  {
        ArrayList<Room> freeRoom = new ArrayList<>();
        for (int i = 0; i < roomDao.getRooms().size(); i++) {
            if (roomDao.getRooms().get(i).getGuests().isEmpty()) {
                freeRoom.add(roomDao.getRooms().get(i));
            }
        }
        log.info(" Колличество свободных комнат: " + freeRoom.size());
        return  freeRoom;
    }

    @Override
    public ArrayList<Room> sortRoomIsFree(LocalDate date) {
        ArrayList<Room> freeRoom = new ArrayList<>();
        try {
            for (int i = 0; i < roomDao.getRooms().size(); i++) {
                if (roomDao.getRooms().get(i).getGuests().isEmpty()) {
                    freeRoom.add(roomDao.getRooms().get(i));
                    log.info(" Свободные комнаты на " + date + " " + roomDao.getRooms().get(i).getRoomNumber());
                } else if (roomDao.getRooms().get(i).getGuests().get(0).getLocalDate().isBefore(date)) {
                    log.info(" Свободные комнаты на " + date + " " + roomDao.getRooms().get(i).getRoomNumber());
                    freeRoom.add(roomDao.getRooms().get(i));
                }
            }
        } catch (Exception exception){
            log.error("Не удалось выполнить действие");
        }

        return freeRoom;
    }

    @Override
    public ArrayList<Guest> getLastThreeGuest(Room room) {
        ArrayList<Guest> guests = new ArrayList<>();
        try {
            if (Prop.getProperties().getBoolean("history", true)) {
                int GuestIndex = room.getLastGuests().size() - 1;
                for (int i = room.getLastGuests().size(); i > room.getLastGuests().size() - 3; i--) {
                    guests.add(room.getLastGuests().get(GuestIndex));
                    log.info(" Последние гости " + room.getLastGuests().get(GuestIndex).getName());
                    GuestIndex--;
                }
            } else {
                log.info("Пока нельзя выполнить данное действие");
                return null;
            }
        } catch (Exception e) {
            log.log(Level.ERROR,"В этом номере не было 3 гостей");
        }
        return guests;
    }

    @Override
    public ArrayList<Room> sortRoomPrice() {
        ArrayList<Room> sortRooms = new ArrayList<>();
        roomDao.getRooms().sort(Comparator.comparing(Room::getBasePrice));
        for (int i = 0; i < roomDao.getRooms().size(); i++) {
            sortRooms.add(roomDao.getRooms().get(i));
            log.info("Цена на номер:  " + roomDao.getRooms().get(i).getRoomNumber() + " " + roomDao.getRooms().get(i).getBasePrice());
        }
        return sortRooms;
    }

    @Override
    public Room getInfoRoom(int index) {
        log.info(" Комнатан находится " + roomDao.getRooms().get(index).getFloor() + " этаже ");
        log.info(" Количество кроватей в комнате " + roomDao.getRooms().get(index).getNumBed());
        log.info(" Стоиость комнаты составит" + roomDao.getRooms().get(index).getBasePrice());
        log.info("Количество звезд у комнаты: " + roomDao.getRooms().get(index).getNumberOfStars());
        try {
            if (roomDao.getRooms().get(index).getGuests().isEmpty()) {
                log.info("В комнате не проживают гости");
            } else {
                log.info("В данный момент комната занята");
            }
        } catch (Exception exception) {
            log.error("Нет гостей");
        }
        return roomDao.getRooms().get(index);
    }

    @Override
    public Room getRoom(int index) {
        return roomDao.getRooms().get(index);
    }

    @Override
    public IRoomDao getRoomDao() {
        return roomDao;
    }


    public List<Room> getRoom(){
        return roomDao.getRooms();
    }

}
