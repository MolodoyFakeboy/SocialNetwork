package org.project.Controller;

import org.project.Model.Guest;
import org.project.Model.Room;
import org.project.Model.Service;

public interface IGuestController {
    Guest getGuest(int index);

    void bookRoom(Room room, Guest guest);

    void leaveHotel(Room room, int guestIndex);

    void useService(Service service, int guestIndex);

    void getaBill(int roomIndex);

    void getNumberGuest();

    void sortUsingServicePrice(int guestIndex);

    void sortUsingServiceTime(int guestIndex);
}
