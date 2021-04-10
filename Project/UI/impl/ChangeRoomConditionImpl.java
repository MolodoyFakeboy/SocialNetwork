package UI.impl;

import Controller.RoomController;
import UI.IAction;

import java.util.Scanner;
import java.util.logging.Logger;

public class ChangeRoomConditionImpl implements IAction {
    private RoomController roomController;
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ChangeRoomConditionImpl.class);


    public ChangeRoomConditionImpl(RoomController roomController) {
        this.roomController = roomController;
    }

    @Override
    public void execute(){
        try {
            Scanner in = new Scanner(System.in);
            log.info("Укажите индекс комнаты");
            int indexRoom = in.nextInt();
            in.nextLine();
            log.info(" 1. Поменять статус у комнаты ");
            log.info(" 2. Поменять цену ");
            roomController.changeRoomCondition(indexRoom);
        } catch (IndexOutOfBoundsException e){
            log.error("Неверный индекс комнаты");
        }
    }
}
