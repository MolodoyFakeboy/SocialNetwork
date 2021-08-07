package org.project.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.project.Configs.ConfigTest;
import org.project.Dao.GuestDao;
import org.project.Model.Guest;
import org.project.Model.Room;
import org.project.Model.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ConfigTest.class)
class GuestServiceTest {

    @Mock
    private GuestDao guestDao;

    private IGuestService guestService;

    @PersistenceContext
    EntityManager entityManager;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        guestService = new GuestService(guestDao);
        Mockito.when(guestDao.getEntityManager()).thenReturn(entityManager);
    }

    @Test
    void addGuest() {
        Guest guest = new Guest("Zahar", "Zahar", "111-111-111");
        guestService.addGuest(guest);
        Mockito.verify(guestDao).add(guest);
        Mockito.verify(guestDao, Mockito.times(1)).add(guest);
    }

    @Test
    void bookRoom() {
        Guest guest = new Guest("Zahar", "Zahar", "111-111-111");
        guest.setId(111);
        Room room1 = new Room(102, 2, 2, 5, 3500);
        room1.setRoomId(111);
        Mockito.when(guestDao.update(guest)).thenReturn(guest);
        Mockito.when(guestDao.find(guest.getId())).thenReturn(guest);
        guestService.bookRoom(guest.getId(), room1);
        Mockito.verify(guestDao).update(guest);
        Mockito.verify(guestDao, Mockito.times(1)).update(guest);

        assertNotNull(guest.getRooms());
    }

    @Test
    void leaveHotel() {
        Guest guest = new Guest("Zahar", "Zahar", "111-111-111");
        guest.setId(111);
        Room room1 = new Room(102, 2, 2, 5, 3500);
        room1.setRoomId(111);
        Mockito.when(guestDao.update(guest)).thenReturn(guest);
        Mockito.when(guestDao.find(guest.getId())).thenReturn(guest);
        guestService.leaveHotel(room1, guest.getId());
        Mockito.verify(guestDao).update(guest);
        Mockito.verify(guestDao, Mockito.times(1)).update(guest);
        assertNotNull(guest.getLastRooms());
    }

    @Test
    void useService() {
        Guest guest = new Guest("Zahar", "Zahar", "111-111-111");
        guest.setId(111);
        Service service = new Service("Чай в номер", 200, "принесем самый вкусный чай");
        Mockito.when(guestDao.update(guest)).thenReturn(guest);
        guestService.useService(guest, service);
        Mockito.verify(guestDao).update(guest);
        assertNotNull(guest.getServices());
    }

    @Test
    void getGuest() {
        Guest guest = new Guest("Zahar", "Zahar", "111-111-111");
        guest.setId(111);
        Mockito.when(guestDao.find(guest.getId())).thenReturn(guest);
        Guest timeGuest = guestService.getGuest(guest.getId());
        Mockito.verify(guestDao).find(guest.getId());

        assertEquals(guest,timeGuest);
    }

    @Test
    void getaBill_whenPersonLiveinThisRoom() {
        int guestID = 1;
        double billForRoom = 8584; // цена комнаты из БД
        double bill = guestService.getaBill(guestID);
        assertEquals(bill, billForRoom);
    }

    @Test
    void getNumberGuest() {
        List<Guest> guestList = guestService.getNumberGuest();
        int guestInHotel = 20;
        assertEquals(guestList.size(), guestInHotel);
    }

    @Test
    void sortUsingServicePrice() {
        int GuestId = 1;
        List<Service> services = guestService.sortUsingServicePrice(GuestId);
        assertNotNull(services);
    }

}