package org.project.Service;

import org.project.Dao.IRoomDao;
import org.project.Model.EnumStatus;
import org.project.Model.Guest;
import org.project.Model.Room;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Stream;


public interface IRoomService {
    Room addNewRoom(Room room);
    Boolean removeRoom(Room room);
    Room changeRoomStatus(Room room, EnumStatus status);
    Room changePriceonRoom(Room room, double price);
    Stream<Room>sortRoomforPrice();
    Stream<Room> sortRoomforBed();
    Stream<Room>sortRoomforStars();
    Stream<Room> sortFreeRoomforPrice() ;
    Stream<Room> sortFreeRoomBed() ;
    Stream<Room> sortFreeRoomStars() throws Exception;
    ArrayList<Room> getAmountFreeRoom() throws Exception;
    ArrayList<Room>sortRoomIsFree(LocalDate date);
    ArrayList<Guest>getLastThreeGuest(Room room);
    ArrayList<Room> sortRoomPrice();
    Room getInfoRoom(int index);
    Room getRoom(int index);

}
