package Controller;

import Annotations.Singleton;
import Dao.IRoomDao;
import Model.EnumStatus;
import Model.Room;
import Service.IRoomService;

import java.time.LocalDate;


@Singleton
public class RoomController implements IRoomController {


    private IRoomService roomService;

    public RoomController(IRoomService roomService) {
        this.roomService = roomService;
    }

    @Override
    public Room getRoom(int index) {
        return roomService.getRoom(index);
    }

    @Override
    public void updateRoom(Room room) {
        roomService.addNewRoom(room);
    }

    @Override
    public void deleatRoom(int roomIndex) {
        roomService.removeRoom(getRoom(roomIndex));
    }

    @Override
    public void changeRoomStatus(int Index) {
        roomService.changeRoomStatus(getRoom(Index), EnumStatus.ROOM_CLEANING);
    }

    @Override
    public void changeRoomPrice(int Index, double price) {
        roomService.changePriceonRoom(getRoom(Index), price);
    }

    @Override
    public void sortRoomforPrice() {
        roomService.sortRoomforPrice();
    }

    @Override
    public void sortRoomforBed() {
        roomService.sortRoomforBed();
    }

    @Override
    public void sortRoomforStars() {
        roomService.sortRoomforBed();
    }

    @Override
    public void sortFreeRoomforPrice() {
        roomService.sortFreeRoomforPrice();
    }

    @Override
    public void sortFreeRoomBed() {
        roomService.sortFreeRoomBed();
    }

    @Override
    public void sortFreeRoomStars() throws Exception {
        roomService.sortFreeRoomStars();
    }

    @Override
    public void getAmountFreeRoom() throws Exception {
        roomService.getAmountFreeRoom();
    }

    @Override
    public void sortRoomIsFree(LocalDate localDate) {
        roomService.sortRoomIsFree(localDate);
    }

    @Override
    public void getLastThreeGuest(int roomIndex) {
        roomService.getLastThreeGuest(getRoom(roomIndex));
    }

    @Override
    public void getInfoAbourRoom(int roomIndex) {
        roomService.getInfoRoom(roomIndex);
    }

    @Override
    public IRoomDao getRoomDao() {
        return roomService.getRoomDao();
    }
}
