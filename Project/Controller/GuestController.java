package Controller;
import Model.Guest;
import Model.Room;
import Model.Service;
import Service.GuestService;

public class GuestController {

    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    public Guest getGuest(int index) {
        return guestService.getGuest(index);
    }

    public void bookRoom(Room room, Guest guest) {
        guestService.bookRoom(room, guest);
    }

    public void leaveHotel(Room room, int guestIndex) {
        guestService.leaveHotel(room, getGuest(guestIndex));
    }

    public void useService(Service service, int guestIndex) {
        guestService.useService(getGuest(guestIndex), service);
    }

    public void getaBill(int guestIndex, Room room) {
        guestService.getaBill(getGuest(guestIndex), room);
    }

    public void getNumberGuest(){
        guestService.getNumberGuest();
    }

    public void sortUsingServicePrice(Guest guest){
        guestService.sortUsingServicePrice(guest);
    }

    public void sortUsingServiceTime(Guest guest){
        guestService.sortUsingServiceTime(guest);
    }

}
