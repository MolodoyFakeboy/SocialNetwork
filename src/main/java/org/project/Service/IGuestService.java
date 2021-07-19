package org.project.Service;

import org.project.Model.Guest;
import org.project.Model.Room;
import org.project.Model.Service;

import java.util.List;

public interface IGuestService {
    Guest addGuest(Guest guest);

    Room bookRoom(int guestID,Room room);

    Boolean leaveHotel(Room room, int guestId);

    Service useService(Guest guest, Service service);

    double getaBill(int guestIndex);

    List<Guest> getNumberGuest();

    List<Service> sortUsingServicePrice(int guestIndex);

    List<Service> sortUsingServiceTime(int guestIndex);

    Guest getGuest(int index);

}
