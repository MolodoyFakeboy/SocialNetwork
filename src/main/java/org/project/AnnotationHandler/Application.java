package org.project.AnnotationHandler;

import org.project.Util.JPAUtility;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public void run() {

        JPAUtility.getEmFactory();

        ApplicationContext javaConfigContext = new AnnotationConfigApplicationContext(Config.class);

    }
}
