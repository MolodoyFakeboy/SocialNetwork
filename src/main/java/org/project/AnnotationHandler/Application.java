package org.project.AnnotationHandler;

import org.project.Controller.MenuController;
import org.project.Dao.RoomDao;
import org.project.UI.MenuBuilder;
import org.project.UI.Navigator;
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

        //установка проперти
        commandListner.inject(prop);

        JsonSaver jsonSaver = context.getObject(JsonSaver.class);

        //создает даошки и добавляет АрейЛист(Не знаю зачем сделал так)
        commandListner.createDao("org/project/Dao", context);

        commandListner.setPackagetoScan("org/project/Dao");

        //создает Сервиса и внедряет за висимость
        commandListner.initDependency("org/project/Service", context);

        commandListner.setPackagetoScan("org/project/Service");

        //Создает контролеры и внедряет за висимость
        commandListner.initDependency("org/project/Controller", context);

        MenuBuilder menuBuilder = new MenuBuilder(context);
        Navigator navigator = new Navigator(menuBuilder.getRootMenu());
        MenuController menuController = new MenuController(menuBuilder, navigator);

        //Десерелизация даошек и обновление их в мапе
        context.update(jsonSaver.deserializationRoom());
        context.update(jsonSaver.deserializationGuest());
        context.update(jsonSaver.deserializationService());

        //задание по @ConfigProperty(configName,propertyName,type)
        commandListner.setValue(context.getObject(RoomDao.class).getRooms().get(0));

        menuController.run();
    }
}
