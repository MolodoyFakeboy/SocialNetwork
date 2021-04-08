package Service;

import Dao.GuestDao;
import Model.Guest;
import Model.Room;
import Model.Service;

import java.util.ArrayList;

public interface IGuestService {
    GuestDao createGuestDao(GuestDao guestDao);
    Room bookRoom(Room room, Guest guest);
    Boolean leaveHotel(Room room, Guest guest);
    Service useService(Guest guest,Service service);
    double getaBill(Guest guest,Room room);
    ArrayList<Guest> getNumberGuest();
    ArrayList<Service> sortUsingServicePrice(Guest guest);
    ArrayList<Service> sortUsingServiceTime(Guest guest);

}
