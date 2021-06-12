package org.project.Service;

import org.project.Model.Guest;
import org.project.Model.Room;
import org.project.Model.Service;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.List;

public interface IGuestService {
    Room bookRoom(Room room, Guest guest, Timestamp departurelDate);
    Boolean leaveHotel(Room room, Guest guest);
    Service useService(Guest guest, Service service);
    void getaBill(int guestIndex);
    List<Guest> getNumberGuest();
    List<Service> sortUsingServicePrice(int guestIndex);
    List<Service> sortUsingServiceTime(int guestIndex);
    Guest getGuest(int index);
    EntityManager getEntityManager();
}
