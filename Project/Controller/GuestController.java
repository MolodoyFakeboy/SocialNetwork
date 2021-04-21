package Controller;

import Annotations.Singleton;
import Dao.IGuestDao;
import Model.Guest;
import Model.Room;
import Model.Service;
import Service.IGuestService;


@Singleton
public class GuestController implements IGuestController {


   private IGuestService guestService;

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
        guestService.leaveHotel(room, getGuest(guestIndex));
    }

    @Override
    public void useService(Service service, int guestIndex) {
        guestService.useService(getGuest(guestIndex), service);
    }

    @Override
    public void getaBill(int guestIndex, Room room) {
        guestService.getaBill(getGuest(guestIndex), room);
    }

    @Override
    public void getNumberGuest(){
        guestService.getNumberGuest();
    }

    @Override
    public void sortUsingServicePrice(Guest guest){
        guestService.sortUsingServicePrice(guest);
    }

    @Override
    public void sortUsingServiceTime(Guest guest){
        guestService.sortUsingServiceTime(guest);
    }

    @Override
    public IGuestDao getGuestDao() {
        return guestService.getGuestDao();
    }


}
