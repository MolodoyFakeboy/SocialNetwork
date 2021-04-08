package UI.impl;

import Controller.RoomController;
import UI.IAction;

import java.util.Scanner;

public class SortRoomActionImpl implements IAction {
    private RoomController roomController;
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(SortRoomActionImpl.class);

    public SortRoomActionImpl(RoomController roomController) {
        this.roomController = roomController;
    }

    @Override
    public void execute() {
        log.info("1. Отсортировать комнаты по цене");
        log.info("2. Отсортировать комнаты по количеству кроватей");
        log.info("3. Отсортировать комнаты по количеству звезд");
        roomController.sortActionRoom();
    }
}
