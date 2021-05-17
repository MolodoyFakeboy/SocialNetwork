package org.project.UI.impl;

import org.project.Controller.IRoomController;
import org.project.UI.IAction;

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
