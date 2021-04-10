package UI.impl;

import Controller.GuestController;
import Controller.RoomController;
import UI.IAction;

import java.util.Scanner;
import java.util.logging.Logger;

public class LeaveHotelActionImpl implements IAction {
    private GuestController guestController;
    private RoomController roomController;
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(LeaveHotelActionImpl.class);

    public LeaveHotelActionImpl(GuestController guestController, RoomController roomController) {
        this.guestController = guestController;
        this.roomController = roomController;
    }


    @Override
    public void execute() {
        try {
            Scanner in = new Scanner(System.in);
            log.info("Укажите индекс комнаты из которой хотите выехать");
            int index = in.nextInt();
            log.info("Укажите ваш индекс");
            int guestIndex = in.nextInt();
            guestController.leaveHotel(roomController.getRoom(index), guestIndex);
        } catch (Exception exception){
            log.error("Неверно указанный индекс");
        }

    }
}
