package src.main.java.Controller;


import src.main.java.Dao.IRoomDao;
import src.main.java.Model.Room;

import java.time.LocalDate;

public interface IRoomController {
    Room getRoom(int index);
    void updateRoom(Room room);
    void deleatRoom(int roomIndex);
    void changeRoomStatus(int Index);
    void changeRoomPrice(int Index, double price);
    void sortRoomforPrice();
    void sortRoomforBed();
    void sortRoomforStars();
    void sortFreeRoomforPrice();
    void sortFreeRoomBed();
    void sortFreeRoomStars() throws Exception;
    void getAmountFreeRoom() throws Exception;
    void sortRoomIsFree(LocalDate localDate);
    void getLastThreeGuest(int roomIndex);
    void getInfoAbourRoom(int roomIndex);
    IRoomDao getRoomDao();
}
