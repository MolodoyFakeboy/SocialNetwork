package Annotations;

import Controller.GuestController;
import Controller.MenuController;
import Controller.RoomController;
import Controller.ServiceController;
import Dao.GuestDao;
import Dao.RoomDao;
import Dao.ServiceDao;
import Service.FunctionService;
import Service.GuestService;
import Service.RoomService;
import UI.MenuBuilder;
import UI.Navigator;
import Util.JsonSaver;
import Util.Prop;

public class Application {

    private CommandListner commandListner;
    private JsonSaver jsonSaver;

    public Application(CommandListner commandListner) {
        this.commandListner = commandListner;
        jsonSaver = new JsonSaver();
    }

    public void run() {

        Prop prop = new Prop();

        ApplicationContext context = new ApplicationContext();
        ObjectFactory objectFactory = new ObjectFactory(context);
        context.setFactory(objectFactory);

        commandListner.inject(prop);
        commandListner.injectPropertyAnnotation(prop);


        commandListner.initDao("Dao",context);

        context.update(jsonSaver.deserializationRoom());
        context.update(jsonSaver.deserializationGuest());
        context.update(jsonSaver.deserializationService());

        commandListner.initDependency("Service",context);

        context.getObject(RoomService.class).setRoomDao(context.getObject(RoomDao.class));
        context.getObject(FunctionService.class).setServiceDao(context.getObject(ServiceDao.class));
        context.getObject(GuestService.class).setGuestDao(context.getObject(GuestDao.class));

        RoomController roomController = new RoomController(context.getObject(RoomService.class));
        ServiceController serviceController = new ServiceController(context.getObject(FunctionService.class));
        GuestController guestController = new GuestController(context.getObject(GuestService.class));


        MenuBuilder menuBuilder = new MenuBuilder(roomController, serviceController, guestController);
        Navigator navigator = new Navigator(menuBuilder.getRootMenu());
        MenuController menuController = new MenuController(menuBuilder, navigator);

        menuController.run();

    }
}
