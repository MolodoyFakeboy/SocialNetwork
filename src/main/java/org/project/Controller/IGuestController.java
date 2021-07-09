package org.project.Controller;

import org.project.Model.Guest;
import org.project.Model.Room;
import org.project.Model.Service;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IGuestController {

    Guest addNewGuest(Guest guest);

    Guest getGuest(int index);

    ResponseEntity<String> bookRoom(int guestID, Room room);

    ResponseEntity<String> leaveHotel(Room room, int guestIndex);

    ResponseEntity<String> useService(Service service, int guestIndex);

    double getaBill(int roomIndex);

    List<Guest> getNumberGuest();

    List<Service> sortUsingServicePrice(int guestIndex);

    List<Service> sortUsingServiceTime(int guestIndex);
}
