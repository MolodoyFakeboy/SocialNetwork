package Dao;
import Model.Room;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RoomDao implements IRoomDao, Serializable {

    private List<Room> rooms;
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(RoomDao.class);

    public RoomDao() {
        rooms  = new ArrayList<>();
    }

    @Override
    public void addRoom(Room room) {
        rooms.add(room);
        log.info(" Добавлена новая комната " + room.toString());
    }

    @Override
    public void removeRoom(Room room) {
        if (rooms.contains(room)) {
            rooms.remove(room);
        }
    }
    public List<Room> getRooms() {
        return rooms;
    }
}
