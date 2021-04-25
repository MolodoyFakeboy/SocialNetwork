package src.main.java.Service;


import src.main.java.Dao.IGuestDao;
import src.main.java.Model.Guest;
import src.main.java.Model.Room;
import src.main.java.Model.Service;

import java.util.List;

public interface IGuestService {
    Room bookRoom(Room room, Guest guest);
    Boolean leaveHotel(Room room, Guest guest);
    Service useService(Guest guest, Service service);
    double getaBill(Guest guest,Room room);
    List<Guest> getNumberGuest();
    List<Service> sortUsingServicePrice(Guest guest);
    List<Service> sortUsingServiceTime(Guest guest);
    Guest getGuest(int index);
    Room getGuestRoom(int index);
    IGuestDao getGuestDao();
}
