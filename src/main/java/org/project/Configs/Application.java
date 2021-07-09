package org.project.Configs;

import org.project.Service.RoomService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public void run() {
        ApplicationContext javaConfigContext = new AnnotationConfigApplicationContext(Config.class);
        RoomService roomService = javaConfigContext.getBean(RoomService.class);
    }
}
