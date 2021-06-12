package org.project.AnnotationHandler;

import org.project.Controller.MenuController;
import org.project.Dao.GuestDao;
import org.project.Dao.RoomDao;
import org.project.Dao.ServiceDao;
import org.project.Service.FunctionService;
import org.project.Service.GuestService;
import org.project.Service.RoomService;
import org.project.UI.MenuBuilder;
import org.project.UI.Navigator;
import org.project.Util.JPAUtility;
import org.project.Util.JsonSaver;
import org.project.Util.Prop;

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
        context.setCommandListner(commandListner);

        JPAUtility.getEmFactory();

        //установка проперти
        commandListner.inject(prop);

        JsonSaver jsonSaver = context.getObject(JsonSaver.class);

        //создает даошки
        commandListner.createSingeltonClasses("org/project/Dao", context);


        //создает Сервиса и внедряет за висимость
        commandListner.createSingeltonClasses("org/project/Service", context);

        commandListner.injectDaoToService(context.getObject(RoomService.class),context.getObject(RoomDao.class));
        commandListner.injectDaoToService(context.getObject(FunctionService.class),context.getObject(ServiceDao.class));
        commandListner.injectDaoToService(context.getObject(GuestService.class),context.getObject(GuestDao.class));

        commandListner.setPackagetoScan("org/project/Service");

        //Создает контролеры и внедряет за висимость
        commandListner.initDependencyContoller("org/project/Controller", context);

        MenuBuilder menuBuilder = new MenuBuilder(context);
        Navigator navigator = new Navigator(menuBuilder.getRootMenu());
        MenuController menuController = new MenuController(menuBuilder, navigator);

        menuController.run();
    }
}
