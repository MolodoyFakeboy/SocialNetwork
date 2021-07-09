package org.project.Controller;

import org.project.Exeception.IdIncorrectData;
import org.project.Model.Guest;
import org.project.Model.Room;
import org.project.Model.Service;
import org.project.Service.IGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class GuestController implements IGuestController {

    private IGuestService guestService;

    @Autowired
    public GuestController(IGuestService guestService) {
        this.guestService = guestService;
    }

    @Override
    @GetMapping("guest/{index}")
    public Guest getGuest(@PathVariable int index) {
        Guest guest = guestService.getGuest(index);
        if (guest == null) {
            throw new NoSuchElementException("There is no guest with such id = " + index + " in shema hotel");
        }
        return guest;
    }

    @Override
    @PostMapping("guest")
    public Guest addNewGuest(@RequestBody Guest guest) {
        return guestService.addGuest(guest);
    }

    @Override
    @PutMapping("guestBR/{guestID}")
    public ResponseEntity<String> bookRoom(@PathVariable int guestID , @RequestBody Room room) {
        guestService.bookRoom( guestID,room);
        return new ResponseEntity<String>("Гость заехал в комнату" + room.getRoomNumber(), HttpStatus.OK);
    }

    @Override
    @PutMapping("leave_guest/{guestIndex}")
    public ResponseEntity<String> leaveHotel(@RequestBody Room room, @PathVariable int guestIndex) {
        guestService.leaveHotel(room, guestIndex);
        return new ResponseEntity<String>("Гость выехал из комнаты в комнату" + room.getRoomNumber(), HttpStatus.OK);
    }

    @Override
    @PutMapping("service_guest/{guestIndex}")
    public ResponseEntity<String> useService(@RequestBody Service service, @PathVariable int guestIndex) {
        guestService.useService(guestService.getGuest(guestIndex), service);
        return new ResponseEntity<String>("Гость воспользовался услугой" + service.getName(), HttpStatus.OK);
    }

    @Override
    @GetMapping("guestBill/{roomIndex}")
    public double getaBill(@PathVariable int roomIndex) {
        return guestService.getaBill(roomIndex);
    }

    @Override
    @GetMapping("guests")
    public List<Guest> getNumberGuest() {
        return guestService.getNumberGuest();
    }

    @Override
    @GetMapping("guestSPrice/{guestIndex}")
    public List<Service> sortUsingServicePrice(@PathVariable int guestIndex) {
        return guestService.sortUsingServicePrice(guestIndex);
    }

    @Override
    @GetMapping("guestSTime/{guestIndex}")
    public List<Service> sortUsingServiceTime(@PathVariable int guestIndex) {
        return guestService.sortUsingServiceTime(guestIndex);
    }

    @ExceptionHandler
    public ResponseEntity<IdIncorrectData> handleException(NoSuchElementException exception){
        IdIncorrectData idIncorrectData = new IdIncorrectData();
        idIncorrectData.setInfo(exception.getMessage());
        return new ResponseEntity<>(idIncorrectData, HttpStatus.NOT_FOUND);
    }
}
