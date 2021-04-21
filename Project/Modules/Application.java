package Modules;

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

    private ICommandListner commandListner;

    public Application(ICommandListner commandListner) {
        this.commandListner = commandListner;
    }

    public void run() {

        Prop prop = new Prop();

        ApplicationContext context = new ApplicationContext();
        ObjectFactory objectFactory = new ObjectFactory(context);
        context.setFactory(objectFactory);

        commandListner.inject(prop);
        commandListner.injectPropertyAnnotation(prop);

        JsonSaver jsonSaver = context.getObject(JsonSaver.class);

        commandListner.initDao("Dao",context);

        context.update(jsonSaver.deserializationRoom());
        context.update(jsonSaver.deserializationGuest());
        context.update(jsonSaver.deserializationService());

        RoomService roomService = new RoomService(context.getObject(RoomDao.class));
        FunctionService functionService = new FunctionService(context.getObject(ServiceDao.class));
        GuestService guestService = new GuestService(context.getObject(GuestDao.class));

        context.put(RoomService.class,roomService);
        context.put(FunctionService.class,functionService);
        context.put(GuestService.class,guestService);

        RoomController roomController = new RoomController(context.getObject(RoomService.class));
        ServiceController serviceController = new ServiceController(context.getObject(FunctionService.class));
        GuestController guestController = new GuestController(context.getObject(GuestService.class));

        context.put(RoomController.class,roomController);
        context.put(ServiceController.class,serviceController);
        context.put(GuestController.class, guestController);

        MenuBuilder menuBuilder = new MenuBuilder(roomController, serviceController, guestController,context);
        Navigator navigator = new Navigator(menuBuilder.getRootMenu());
        MenuController menuController = new MenuController(menuBuilder, navigator);

        menuController.run();

    }
}
