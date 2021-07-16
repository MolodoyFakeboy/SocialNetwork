package org.project;

import org.project.Configs.Config;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args)  {
        ApplicationContext javaConfigContext = new AnnotationConfigApplicationContext(Config.class);
    }
}
