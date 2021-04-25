package src.main.java.Dao;


import src.main.java.Model.Room;

import java.util.List;

public interface IRoomDao {
     void addRoom(Room room);
    void removeRoom(Room room);
    List<Room> getRooms();

}
