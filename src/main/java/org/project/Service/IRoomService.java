package org.project.Service;

import org.project.Model.EnumStatus;
import org.project.Model.Guest;
import org.project.Model.Room;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

public interface IRoomService {
    Room addNewRoom(Room room);

    Boolean removeRoom(int id);

    Room changeRoomStatus(Room room, EnumStatus status);

    Room changePriceonRoom(Room room, double price);

    List<Room> sortRoomforPrice();

    List<Room> sortRoomforBed();

    List<Room> sortRoomforStars();

    List<Room> sortFreeRoomforPrice();

    List<Room> sortFreeRoomBed();

    List<Room> sortFreeRoomStars() throws Exception;

    List<Room> getAmountFreeRoom() throws Exception;

    List<Room> sortRoomIsFree(Date date) throws ParseException;

    List<Guest> getLastThreeGuest(int index);

    Room getRoom(int index);
}
