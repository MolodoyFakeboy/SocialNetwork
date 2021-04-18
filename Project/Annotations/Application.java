package Annotations;

import Controller.GuestController;
import Controller.MenuController;
import Controller.RoomController;
import Controller.ServiceController;
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

        RoomController roomController = new RoomController();
        ServiceController serviceController = new ServiceController();
        GuestController guestController = new GuestController();

        commandListner.configure(roomController,context);
        commandListner.configure(serviceController,context);
        commandListner.configure(guestController,context);

        MenuBuilder menuBuilder = new MenuBuilder(roomController, serviceController, guestController);
        Navigator navigator = new Navigator(menuBuilder.getRootMenu());
        MenuController menuController = new MenuController(menuBuilder, navigator);

        menuController.run();

    }
}
