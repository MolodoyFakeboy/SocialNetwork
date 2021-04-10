package UI.impl;

import Controller.RoomController;
import Service.RoomService;
import UI.IAction;
import java.util.Scanner;


public class getThreeLastGuestImpl implements IAction {
    private RoomController roomController;
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(getThreeLastGuestImpl.class);

    public getThreeLastGuestImpl(RoomController roomController) {
        this.roomController = roomController;
    }

    @Override
    public void execute()  {
        try {
            Scanner in = new Scanner(System.in);
            log.info("Укажите индекс комнаты в которой хотите узнать последних гостей");
            int index = in.nextInt();
            roomController.getLastThreeGuest(index);
        }catch (Exception exception){
            log.error("Неверный формат");
        }


    }
}
