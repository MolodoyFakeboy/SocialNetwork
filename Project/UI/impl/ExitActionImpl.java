package UI.impl;

import Controller.GuestController;
import Controller.RoomController;
import Controller.ServiceController;
import Dao.GuestDao;
import Dao.RoomDao;
import Dao.ServiceDao;
import UI.IAction;
import Util.JsonSaver;

import java.io.IOException;

public class ExitActionImpl implements IAction {

    private GuestController guestController;
    private RoomController roomController;
    private ServiceController serviceController;
    private JsonSaver jsonSaver;
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ExitActionImpl.class);

    public ExitActionImpl(GuestController guestController, RoomController roomController, ServiceController serviceController) {
         jsonSaver = new JsonSaver();
        this.guestController = guestController;
        this.roomController = roomController;
        this.serviceController = serviceController;
    }

    @Override
    public void execute() {
        try {
            RoomDao roomDao = roomController.getRoomDao();
            GuestDao guestDao = guestController.getGuestDao();
            ServiceDao serviceDao = serviceController.getServiceDao();
            jsonSaver.searilization(roomDao,guestDao,serviceDao);
            System.exit(0);
        } catch (IOException e) {
            log.error("Ошибка сереализации");
        }
    }
}
