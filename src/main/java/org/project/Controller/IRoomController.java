package org.project.Controller;

import org.project.Model.EnumStatus;
import org.project.Model.Guest;
import org.project.Model.Room;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

public interface IRoomController {
    Room getRoom(int index);

    Room updateRoom(Room room);

    ResponseEntity<String> deleatRoom(int id);

    Room changeRoomStatus(int index, EnumStatus status);

    Room changeRoomPrice(int index, double price);

    List<Room> sortRoomforPrice();

    List<Room> sortRoomforBed();

    List<Room> sortRoomforStars();

    List<Room> sortFreeRoomforPrice();

    List<Room> sortFreeRoomBed();

    List<Room> sortFreeRoomStars() throws Exception;

    ResponseEntity<String> getAmountFreeRoom() throws Exception;

    List<Room> sortRoomIsFree(Date date) throws ParseException;

    List<Guest> getLastThreeGuest(int roomIndex);
}
