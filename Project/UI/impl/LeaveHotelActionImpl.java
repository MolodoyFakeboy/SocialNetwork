package UI.impl;

import Controller.IGuestController;
import Controller.IRoomController;
import UI.IAction;

import java.util.Scanner;

public class LeaveHotelActionImpl implements IAction {
    private IGuestController guestController;
    private IRoomController roomController;
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(LeaveHotelActionImpl.class);

    public LeaveHotelActionImpl(IGuestController guestController, IRoomController roomController) {
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
