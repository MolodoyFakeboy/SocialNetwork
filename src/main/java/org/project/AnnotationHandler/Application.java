package org.project.AnnotationHandler;

import org.project.Controller.GuestController;
import org.project.Controller.MenuController;
import org.project.Controller.RoomController;
import org.project.Controller.ServiceController;
import org.project.UI.MenuBuilder;
import org.project.UI.Navigator;
import org.project.Util.JPAUtility;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public void run() {

        JPAUtility.getEmFactory();

        ApplicationContext javaConfigContext = new AnnotationConfigApplicationContext(Config.class);

        MenuBuilder menuBuilder =
                new MenuBuilder(javaConfigContext.getBean(RoomController.class),
                        javaConfigContext.getBean(ServiceController.class),
                        javaConfigContext.getBean(GuestController.class));
        Navigator navigator = new Navigator(menuBuilder.getRootMenu());
        MenuController menuController = new MenuController(menuBuilder, navigator);

        menuController.run();
    }
}
