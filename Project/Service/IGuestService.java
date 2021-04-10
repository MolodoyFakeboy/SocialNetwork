package Service;

import Dao.GuestDao;
import Model.Guest;
import Model.Room;
import Model.Service;
import java.util.List;

public interface IGuestService {
    GuestDao createGuestDao(GuestDao guestDao);
    Room bookRoom(Room room, Guest guest);
    Boolean leaveHotel(Room room, Guest guest);
    Service useService(Guest guest,Service service);
    double getaBill(Guest guest,Room room);
    List<Guest> getNumberGuest();
    List<Service> sortUsingServicePrice(Guest guest);
    List<Service> sortUsingServiceTime(Guest guest);
}
