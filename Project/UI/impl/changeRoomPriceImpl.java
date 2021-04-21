package UI.impl;

import Controller.IRoomController;
import UI.IAction;

import java.util.Scanner;

public class changeRoomPriceImpl implements IAction {

    private IRoomController roomController;
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(changeRoomPriceImpl.class);

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
