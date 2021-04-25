package src.main.java.UI.impl;



import src.main.java.Dao.GuestDao;
import src.main.java.Dao.RoomDao;
import src.main.java.Dao.ServiceDao;
import src.main.java.Modules.ApplicationContext;
import src.main.java.UI.IAction;
import src.main.java.Util.JsonSaver;

import java.io.IOException;

public class ExitActionImpl implements IAction {

    private ApplicationContext context;
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ExitActionImpl.class);

    public ExitActionImpl(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void execute() {
        try {
            RoomDao roomDao = context.getObject(RoomDao.class);
            GuestDao guestDao = context.getObject(GuestDao.class);
            ServiceDao serviceDao = context.getObject(ServiceDao.class);
            context.getObject(JsonSaver.class).searilization(roomDao,guestDao,serviceDao);
            System.exit(0);
        } catch (IOException e) {
            log.error("Ошибка сереализации");
        }
    }
}
