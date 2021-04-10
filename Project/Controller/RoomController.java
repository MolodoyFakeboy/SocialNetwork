package Controller;

import Resources.EnumStatus;
import Model.Room;
import Service.RoomService;

import java.time.LocalDate;
import java.util.Scanner;

public class RoomController {

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

    public void changeRoomCondition(int roomIndex) {
        Scanner in = new Scanner(System.in);
        int action = in.nextInt();
        switch (action) {
            case 1:
                roomService.changeRoomStatus(getRoom(roomIndex), EnumStatus.ROOM_CLEANING);
                break;
            case 2:
                double price = in.nextDouble();
                roomService.changePriceonRoom(getRoom(roomIndex), price);
                break;
            default:
                break;
        }
    }

    public void sortActionRoom(){
        Scanner in = new Scanner(System.in);
        int action = in.nextInt();
        switch (action) {
            case 1 : roomService.sortRoomforPrice();
            break;
            case 2 : roomService.sortRoomforBed();
            break;
            case 3 : roomService.sortRoomforStars();
            break;
            default:
                break;
        }
    }

    public void sortActionFreeRoom()  {
        Scanner in = new Scanner(System.in);
        int action = in.nextInt();
        switch (action) {
            case 1 : roomService.sortFreeRoomforPrice();
                break;
            case 2 : roomService.sortFreeRoomBed();
                break;
            case 3 : roomService.sortFreeRoomStars();
                break;
            case 4 : roomService.getAmountFreeRoom();
            default:
                break;
        }
    }

    public void sortRoomIsFree(LocalDate localDate) {
            roomService.sortRoomIsFree(localDate);
    }

    public void getLastThreeGuest(int roomIndex){
        roomService.getLastThreeGuest(getRoom(roomIndex));
    }

    public void getInfoAbourRoom(int roomIndex){
        roomService.getInfoRoom(roomIndex);
    }



}
