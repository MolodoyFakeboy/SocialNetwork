package src.main.java.Controller;


import src.main.java.Dao.IGuestDao;
import src.main.java.Model.Guest;
import src.main.java.Model.Room;
import src.main.java.Model.Service;

public interface IGuestController {
    Guest getGuest(int index);
    void bookRoom(Room room, Guest guest);
    void leaveHotel(Room room, int guestIndex);
    void useService(Service service, int guestIndex);
    void getaBill(int guestIndex, Room room);
    void getNumberGuest();
    void sortUsingServicePrice(Guest guest);
    void sortUsingServiceTime(Guest guest);
    IGuestDao getGuestDao();

}
