package org.project.UI.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.project.Controller.IRoomController;
import org.project.UI.IAction;

import java.util.Scanner;

public class changeRoomPriceImpl implements IAction {

    private IRoomController roomController;
    private static final Logger log = LogManager.getLogger(changeRoomPriceImpl.class);

    public changeRoomPriceImpl(IRoomController roomController) {
        this.roomController = roomController;
    }

    @Override
    public void execute() throws Exception {
        try {
            Scanner in = new Scanner(System.in);
            log.info("Укажите индекс комнаты");
            int indexRoom = in.nextInt();
            log.info("Укажите цену у комнаты");
            double price = in.nextDouble();
            roomController.changeRoomPrice(indexRoom,price);
        } catch (IndexOutOfBoundsException e){
            log.error("Неверный индекс");
        }
    }
}
