package org.project.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.project.Configs.ConfigTest;
import org.project.Dao.RoomDao;
import org.project.Model.EnumStatus;
import org.project.Model.Guest;
import org.project.Model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.Field;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ConfigTest.class)
class RoomServiceTest {

    @Mock
    private RoomDao roomDao;

    private RoomService roomService;

    @PersistenceContext
    EntityManager entityManager;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        roomService = new RoomService(roomDao);
        Mockito.when(roomDao.getEntityManager()).thenReturn(entityManager);
    }

    @Test
    void testAddNewRoom() {
        Room room1 = new Room(102, 2, 2, 5, 3500);
        roomService.addNewRoom(room1);
        Mockito.verify(roomDao).add(room1);
        Mockito.verify(roomDao, Mockito.times(1)).add(room1);
    }


    @Test
    void testRemoveRoom() {
        Room room1 = new Room(102, 2, 2, 5, 3500);
        room1.setRoomId(108);
        roomService.removeRoom(room1.getRoomId());
        Mockito.verify(roomDao).delete(room1.getRoomId());
        Mockito.verify(roomDao, Mockito.times(1)).delete(room1.getRoomId());
    }

    @Test
    void testChangeRoomStatus() throws IllegalAccessException {
        Room room1 = new Room(102, 2, 2, 5, 3500);
        room1.setRoomId(108);
        Mockito.when(roomDao.update(room1)).thenReturn(room1);
        Field [] fields = roomService.getClass().getDeclaredFields();
        for (Field field : fields){
            if(field.isAnnotationPresent(Value.class)){
                field.setAccessible(true);
                field.set(roomService,true);
            }
        }
        roomService.changeRoomStatus(room1, EnumStatus.BOOK_ROOM);
        Mockito.verify(roomDao).update(room1);
    }

    @Test
    void testChangePriceonRoom() {
        Room room1 = new Room(102, 2, 2, 5, 3500);
        room1.setRoomId(108);
        Mockito.when(roomDao.update(room1)).thenReturn(room1);
        roomService.changePriceonRoom(room1, 2000);
        Mockito.verify(roomDao).update(room1);
    }


    @Test
    void testGetRoom() {
        Room room1 = new Room(102, 2, 2, 5, 3500);
        room1.setRoomId(108);
        Mockito.when(roomDao.find(room1.getRoomId())).thenReturn(room1);
        roomService.getRoom(room1.getRoomId());
        Mockito.verify(roomDao).find(room1.getRoomId());
    }

    @Test
    void testSortRoomforPrice() {
        List<Room> rooms = roomService.sortRoomforPrice();
        assertNotNull(rooms);
        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    @Test
    void testSortRoomforBed() {
        List<Room> rooms = roomService.sortRoomforBed();
        assertNotNull(rooms);
        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    @Test
    void testSortRoomforStars() {
        List<Room> rooms = roomService.sortRoomforStars();
        assertNotNull(rooms);
        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    @Test
    void testSortFreeRoomforPrice() {
        List<Room> rooms = roomService.sortFreeRoomforPrice();
        assertNotNull(rooms);
        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    @Test
    void testSortFreeRoomBed() {
        List<Room> rooms = roomService.sortFreeRoomBed();
        assertNotNull(rooms);
        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    @Test
    void testSortFreeRoomStars() {
        List<Room> rooms = roomService.sortFreeRoomStars();
        assertNotNull(rooms);
        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    @Test
    void testGetAmountFreeRoom() {
        List<Room> rooms = roomService.getAmountFreeRoom();
        assertNotNull(rooms);
        System.out.println(rooms.size());
    }

    @Test
    void testSortRoomIsFree() {
        Date date = Date.valueOf("2021-08-01");
        List<Room> rooms = roomService.sortRoomIsFree(date);
        assertNotNull(rooms);
        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    @Test
    void testGetLastThreeGuest() throws IllegalAccessException{
        int roomID = 1;
        Field [] fields = roomService.getClass().getDeclaredFields();
        for (Field field : fields){
            if(field.isAnnotationPresent(Value.class)){
                field.setAccessible(true);
                field.set(roomService,true);
            }
        }
        List<Guest> guests = roomService.getLastThreeGuest(roomID);
        assertNotNull(guests);
        for (Guest guest : guests) {
            System.out.println(guest);
        }
    }

}