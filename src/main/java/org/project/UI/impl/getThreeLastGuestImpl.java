package org.project.UI.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.project.Controller.IRoomController;
import org.project.UI.IAction;

import java.util.Scanner;
public class getThreeLastGuestImpl implements IAction {
    private IRoomController roomController;
    private static final Logger log = LogManager.getLogger(getThreeLastGuestImpl.class);

    public getThreeLastGuestImpl(IRoomController roomController) {
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
