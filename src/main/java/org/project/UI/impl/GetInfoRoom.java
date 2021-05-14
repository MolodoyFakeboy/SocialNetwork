package UI.impl;



import Controller.IRoomController;
import UI.IAction;

import java.util.Scanner;

public class GetInfoRoom implements IAction {
    private IRoomController roomController;
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(GetInfoRoom.class);

    public GetInfoRoom(IRoomController roomController) {
        this.roomController = roomController;
    }

    @Override
    public void execute() {
        try {
            Scanner in = new Scanner(System.in);
            log.info("Укажите индекс комнаты");
            int roomindex = in.nextInt();
            roomController.getInfoAbourRoom(roomindex);
        } catch (IndexOutOfBoundsException e){
            log.error("Нет номера под таким индексом");
        } catch (Exception e){
            log.error("Неверно указаный индекс");
        }

    }

}

