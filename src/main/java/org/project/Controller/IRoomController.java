package org.project.Controller;

import org.project.Model.EnumStatus;
import org.project.Model.Room;

import java.sql.Date;
import java.text.ParseException;

public interface IRoomController {
    Room getRoom(int index);

    void updateRoom(Room room);

    void deleatRoom(int roomIndex);

    void changeRoomStatus(int Index, EnumStatus status);

    void changeRoomPrice(int Index, double price);

    void sortRoomforPrice();

    void sortRoomforBed();

    void sortRoomforStars();

    void sortFreeRoomforPrice();

    void sortFreeRoomBed();

    void sortFreeRoomStars() throws Exception;

    void getAmountFreeRoom() throws Exception;

    void sortRoomIsFree(Date date) throws ParseException;

    void getLastThreeGuest(int roomIndex);

    void getInfoAbourRoom(int roomIndex);
}
