package src.main.java.Service;

import src.main.java.Annotations.InjectByType;
import src.main.java.Annotations.Singleton;
import src.main.java.Dao.IGuestDao;
import src.main.java.Model.EnumStatus;
import src.main.java.Model.Guest;
import src.main.java.Model.Room;
import src.main.java.Model.Service;
import org.apache.log4j.Logger;

import java.util.Comparator;
import java.util.Date;
import java.util.List;


@Singleton
public class GuestService implements IGuestService {

    @InjectByType
    private IGuestDao guestDao;

    private Logger log;

    public GuestService(IGuestDao guestDao)
    {
        this.guestDao = guestDao;
        log = Logger.getLogger(GuestService.class);
    }

    @Override
    public Room bookRoom(Room room, Guest guest) {
        try {
            Date date = new Date();
            guestDao.addGuest(guest);
            guest.setArrivalDate(date);
            room.addGuestRoom(guest);
            room.setStatus(EnumStatus.BOOK_ROOM);
            guest.addRoom(room);
        } catch (Exception exception){
            log.error("Не получилось выполнить действие");
        }
        return room;
    }

    @Override
    public Boolean leaveHotel(Room room, Guest guest) {
        try {
            guestDao.deletGuest(guest);
            room.deletGuest(guest);
            room.setStatus(EnumStatus.FREE_ROOM);
            room.addLastGuest(guest);
        } catch (Exception e){
            log.error("Не удалось выполнить действие");
        }

        return true;
    }

    @Override
    public Service useService(Guest guest, Service service) {
        guest.addService(service);
        Date date = new Date();
        service.setDate(date);
        log.info(" Гость воспользовался услугой " + service.getName());
        return service;
    }

    @Override
    public double getaBill(Guest guest, Room room) {
        double BillForNumber;
        BillForNumber = guest.getRooms().get(guest.getRooms().indexOf(room)).getBasePrice();
        System.out.println("Оплата за номер составит" + BillForNumber);
        return BillForNumber;
    }

    @Override
    public List<Guest> getNumberGuest() {
        System.out.println(" Колличество гостей в отеле " + guestDao.getGuests().size());
        return guestDao.getGuests();
    }

    @Override
    public List<Service> sortUsingServicePrice(Guest guest) {
       try {
           guest.getServices().sort(Comparator.comparing(Service::getPrice));
           for (int i = 0; i < guest.getServices().size(); i++) {
               log.info(" Гость воспользовался услугами " + guest.getServices().get(i) + " в " + guest.getServices().get(i).getDate());
           }
       } catch (Exception e){
           log.error("Не удалось выполнить действие");
       }
        return guest.getServices();
    }

    @Override
    public List<Service> sortUsingServiceTime(Guest guest) {
        try {
            guest.getServices().sort(Comparator.comparing(Service::getDate));
            for (int i = 0; i < guest.getServices().size(); i++) {
                log.info(" в " + guest.getServices().get(i).getDate() + " Гость воспользовался услугами " + guest.getServices().get(i));
            }
        } catch (Exception exception){
            log.error("Не удалось выполнить действие");
        }
        return guest.getServices();
    }

    @Override
    public Guest getGuest(int index){
        return guestDao.getGuests().get(index);
    }

    @Override
    public Room getGuestRoom(int index){
        return guestDao.getGuests().get(index).getRooms().get(0);
    }

    @Override
    public IGuestDao getGuestDao() {
        return guestDao;
    }

}
