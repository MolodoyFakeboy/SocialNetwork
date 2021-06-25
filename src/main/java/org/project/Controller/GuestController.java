package org.project.Controller;

import org.project.Model.Guest;
import org.project.Model.Room;
import org.project.Model.Service;
import org.project.Service.IGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class GuestController implements IGuestController {

    private IGuestService guestService;

    @Autowired
    public GuestController(IGuestService guestService) {
        this.guestService = guestService;
    }

    @Override
    public Guest getGuest(int index) {
        return guestService.getGuest(index);
    }

    @Override
    public void bookRoom(Room room, Guest guest) {
        guestService.bookRoom(room, guest);
    }

    @Override
    public void leaveHotel(Room room, int guestIndex) {
        guestService.leaveHotel(room, guestIndex);
    }

    @Override
    public void useService(Service service, int guestIndex) {
        guestService.useService(getGuest(guestIndex), service);
    }

    @Override
    public void getaBill(int roomIndex) {
        guestService.getaBill(roomIndex);
    }

    @Override
    public void getNumberGuest() {
        guestService.getNumberGuest();
    }

    @Override
    public void sortUsingServicePrice(int guestIndex) {
        guestService.sortUsingServicePrice(guestIndex);
    }

    @Override
    public void sortUsingServiceTime(int guestIndex) {
        guestService.sortUsingServiceTime(guestIndex);
    }

}
