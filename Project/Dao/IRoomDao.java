package Dao;


import Model.Room;

import java.util.List;

public interface IRoomDao {
     void addRoom(Room room);
    void removeRoom(Room room);
    List<Room> getRooms();

}
