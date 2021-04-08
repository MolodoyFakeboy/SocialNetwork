package UI.impl;

import Controller.GuestController;
import Controller.RoomController;
import UI.IAction;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Logger;

public class GetBillActionImpl implements IAction {
    private GuestController guestController;
    private RoomController roomController;
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(GetBillActionImpl.class);

    public GetBillActionImpl(GuestController guestController, RoomController roomController) {
        this.guestController = guestController;
        this.roomController = roomController;
    }

    @Override
    public void execute(){
        try {
            Scanner in = new Scanner(System.in);
            log.info("Укажите ваш ID");
            int index = in.nextInt();
            log.info("Укажите индекс вашей комнаты");
            int indexRoom = in.nextInt();
            guestController.getaBill(index,roomController.getRoom(indexRoom));
        } catch (NoSuchElementException exception){
            log.error("нет елемента под таким индексом");
        } catch (Exception e){
            log.error("Неверно указанный индекс");
        }

    }
}
