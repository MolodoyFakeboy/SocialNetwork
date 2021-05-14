package AnnotationHandler;

import Controller.MenuController;
import Dao.RoomDao;
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
        context.setCommandListner(commandListner);

        //установка проперти
        commandListner.inject(prop);

        JsonSaver jsonSaver = context.getObject(JsonSaver.class);

        //создает даошки и добавляет АрейЛист(Не знаю зачем сделал так)
        commandListner.createDao("Dao", context);

        commandListner.setPackagetoScan("Dao");

        //создает Сервиса и внедряет за висимость
        commandListner.initDependency("Service", context);

        commandListner.setPackagetoScan("Service");

        //Создает контролеры и внедряет за висимость
        commandListner.initDependency("Controller", context);

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
