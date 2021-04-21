package Controller;

import Annotations.InjectByType;
import Annotations.Singleton;
import Dao.RoomDao;
import Model.EnumStatus;
import Model.Room;
import Service.RoomService;

import java.time.LocalDate;


@Singleton
public class RoomController {

    @InjectByType
    private RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    public Room getRoom(int index) {
        return roomService.getRoom().get(index);
    }

    public void updateRoom(Room room) {
        roomService.addNewRoom(room);
    }

    public void deleatRoom(int roomIndex) {
        roomService.removeRoom(getRoom(roomIndex));
    }

    public void changeRoomStatus(int Index) {
        roomService.changeRoomStatus(getRoom(Index), EnumStatus.ROOM_CLEANING);
    }

    public void changeRoomPrice(int Index, double price) {
        roomService.changePriceonRoom(getRoom(Index), price);
    }

    public void sortRoomforPrice() {
        roomService.sortRoomforPrice();
    }

    public void sortRoomforBed() {
        roomService.sortRoomforBed();
    }

    public void sortRoomforStars() {
        roomService.sortRoomforBed();
    }

    public void sortFreeRoomforPrice() {
        roomService.sortFreeRoomforPrice();
    }

    public void sortFreeRoomBed() {
        roomService.sortFreeRoomBed();
    }

    public void sortFreeRoomStars() {
        roomService.sortFreeRoomStars();
    }

    public void getAmountFreeRoom() {
        roomService.getAmountFreeRoom();
    }

    public void sortRoomIsFree(LocalDate localDate) {
        roomService.sortRoomIsFree(localDate);
    }

    public void getLastThreeGuest(int roomIndex) {
        roomService.getLastThreeGuest(getRoom(roomIndex));
    }

    public void getInfoAbourRoom(int roomIndex) {
        roomService.getInfoRoom(roomIndex);
    }

    public RoomDao getRoomDao() {
        return roomService.getRoomDao();
    }
}
