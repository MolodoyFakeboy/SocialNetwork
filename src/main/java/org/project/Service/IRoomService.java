package org.project.Service;

import org.project.Model.EnumStatus;
import org.project.Model.Guest;
import org.project.Model.Room;

import java.sql.Date;
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

    List<Room> sortFreeRoomStars();

    List<Room> getAmountFreeRoom();

    List<Room> sortRoomIsFree(Date date);

    List<Guest> getLastThreeGuest(int index);

    Room getRoom(int index);
}
