package org.project.Service;

import org.project.Model.Guest;
import org.project.Model.Room;
import org.project.Model.Service;

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
}
