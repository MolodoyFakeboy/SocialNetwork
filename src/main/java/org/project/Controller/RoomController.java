package org.project.Controller;

import org.project.Exeception.IdIncorrectData;
import org.project.Model.EnumStatus;
import org.project.Model.Guest;
import org.project.Model.Room;
import org.project.Service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class RoomController implements IRoomController {

    private IRoomService roomService;

    @Autowired
    public RoomController(IRoomService roomService) {
        this.roomService = roomService;
    }

    @Override
    @GetMapping("room/{index}")
    public Room getRoom(@PathVariable int index) {
        Room room = roomService.getRoom(index);
        if (room == null) {
            throw new NoSuchElementException("There is no room with such id = " + index + " in shema hotel");
        }
        return room;
    }

    @Override
    @PostMapping("room")
    public Room updateRoom(@RequestBody Room room) {
        return roomService.addNewRoom(room);
    }

    @Override
    @DeleteMapping("room/{id}")
    public ResponseEntity<String> deleatRoom(@PathVariable int id) {
        roomService.removeRoom(id);
        return new ResponseEntity<String>("room with ID = " + id + "was deleated", HttpStatus.OK);
    }

    @Override
    @PutMapping("roomStatus/{index}/{status}")
    public Room changeRoomStatus(@PathVariable int index, @PathVariable EnumStatus status) {
        return roomService.changeRoomStatus(roomService.getRoom(index), status);
    }

    @Override
    @PutMapping("roomPrice/{index}/{price}")
    public Room changeRoomPrice(@PathVariable int index, @PathVariable double price) {
        return roomService.changePriceonRoom(roomService.getRoom(index), price);
    }

    @Override
    @GetMapping("roomsPrice")
    public List<Room> sortRoomforPrice() {
        return roomService.sortRoomforPrice();
    }

    @Override
    @GetMapping("roomsBed")
    public List<Room> sortRoomforBed() {
        return roomService.sortRoomforBed();
    }

    @Override
    @GetMapping("roomsStars")
    public List<Room> sortRoomforStars() {
        return roomService.sortRoomforBed();
    }

    @Override
    @GetMapping("free/roomsPrice")
    public List<Room> sortFreeRoomforPrice() {
        return roomService.sortFreeRoomforPrice();
    }

    @Override
    @GetMapping("free/roomsBed")
    public List<Room> sortFreeRoomBed() {
        return roomService.sortFreeRoomBed();
    }

    @Override
    @GetMapping("free/roomsStars")
    public List<Room> sortFreeRoomStars() throws Exception {
        return roomService.sortFreeRoomStars();
    }

    @Override
    @GetMapping("free/roomsAmount")
    public ResponseEntity<String> getAmountFreeRoom() throws Exception {
        int amountRooms = roomService.getAmountFreeRoom().size();
        return new ResponseEntity<String> (" Количество свободных комнат: " + amountRooms, HttpStatus.OK);
    }

    @Override
    @GetMapping("rooms/{date}")
    public List<Room> sortRoomIsFree(@PathVariable Date date) throws ParseException {
        return roomService.sortRoomIsFree(date);
    }

    @Override
    @GetMapping("roomsGuest/{roomIndex}")
    public  List<Guest> getLastThreeGuest(@PathVariable int roomIndex) {
        return roomService.getLastThreeGuest(roomIndex);
    }

    @ExceptionHandler
    public ResponseEntity<IdIncorrectData> handleException(NoSuchElementException exception){
        IdIncorrectData idIncorrectData = new IdIncorrectData();
        idIncorrectData.setInfo(exception.getMessage());
        return new ResponseEntity<>(idIncorrectData, HttpStatus.NOT_FOUND);
    }

}
