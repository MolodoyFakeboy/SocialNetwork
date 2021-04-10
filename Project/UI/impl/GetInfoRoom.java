package UI.impl;

import Controller.RoomController;
import UI.IAction;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Logger;

public class GetInfoRoom implements IAction {
    private RoomController roomController;
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(GetInfoRoom.class);

    public GetInfoRoom(RoomController roomController) {
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

