package src.main.java.UI.impl;

import src.main.java.Controller.IRoomController;
import src.main.java.UI.IAction;

import java.util.Scanner;

public class changeRoomStatusImpl implements IAction {

    private IRoomController roomController;
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(changeRoomStatusImpl.class);

    public changeRoomStatusImpl(IRoomController roomController) {
        this.roomController = roomController;
    }

    @Override
    public void execute() throws Exception {
        try {
            Scanner in = new Scanner(System.in);
            log.info("Укажите индекс комнаты");
            int indexRoom = in.nextInt();
            roomController.changeRoomStatus(indexRoom);
        } catch (IndexOutOfBoundsException e){
            log.error("Неверный индекс");
        }
    }
}
