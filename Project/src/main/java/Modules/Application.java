package src.main.java.Modules;

import src.main.java.Controller.GuestController;
import src.main.java.Controller.MenuController;
import src.main.java.Controller.RoomController;
import src.main.java.Controller.ServiceController;
import src.main.java.Dao.GuestDao;
import src.main.java.Dao.RoomDao;
import src.main.java.Dao.ServiceDao;
import src.main.java.Service.FunctionService;
import src.main.java.Service.GuestService;
import src.main.java.Service.RoomService;
import src.main.java.UI.MenuBuilder;
import src.main.java.UI.Navigator;
import src.main.java.Util.JsonSaver;
import src.main.java.Util.Prop;


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

        commandListner.initDao("src/main/java/Dao",context);

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
