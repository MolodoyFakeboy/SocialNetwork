package org.project.Controller;

import org.project.Model.Guest;
import org.project.Model.Room;
import org.project.Model.Service;

public interface IGuestController {
    Guest getGuest(int index);
    void bookRoom(Room room, Guest guest);
    void leaveHotel(Room room, int guestIndex);
    void useService(Service service, int guestIndex);
    void getaBill(int guestIndex, Room room);
    void getNumberGuest();
    void sortUsingServicePrice(Guest guest);
    void sortUsingServiceTime(Guest guest);

}
