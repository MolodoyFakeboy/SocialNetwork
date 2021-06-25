package org.project.UI.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.project.Controller.IRoomController;
import org.project.Model.EnumStatus;
import org.project.UI.IAction;

import java.util.Scanner;

public class changeRoomStatusImpl implements IAction {

    private IRoomController roomController;
    private static final Logger log = LogManager.getLogger(changeRoomStatusImpl.class);

    public changeRoomStatusImpl(IRoomController roomController) {
        this.roomController = roomController;
    }

    @Override
    public void execute() throws Exception {
        try {
            Scanner in = new Scanner(System.in);
            log.info("Укажите индекс комнаты");
            int indexRoom = in.nextInt();
            log.info("если комната свободна нажмите 1");
            log.info("если в комнате проживают гости нажмите 2");
            int indexAction = in.nextInt();
            switch (indexAction){
                case 1 : roomController.changeRoomStatus(indexRoom, EnumStatus.FREE_ROOM);
                    break;
                case 2 : roomController.changeRoomStatus(indexRoom, EnumStatus.BOOK_ROOM);
                    break;
                default:
                    log.info("Oooops, something wrong !");
            }

        } catch (IndexOutOfBoundsException e){
            log.error("Неверный индекс");
        }
    }
}
