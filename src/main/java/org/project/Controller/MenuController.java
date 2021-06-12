package org.project.Controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.project.UI.MenuBuilder;
import org.project.UI.Navigator;

import java.util.Scanner;

public class MenuController {

    private final MenuBuilder builder;
    private final Navigator navigator;

    private static final Logger log = LogManager.getLogger(MenuController.class);

    public MenuController(MenuBuilder builder, Navigator navigator) {
        this.builder = builder;
        this.navigator = navigator;
    }

    public void run() {
        try {
            Scanner in = new Scanner(System.in);
            builder.buildMenu();
            navigator.printMenu();
            while (true) {
                Integer index = in.nextInt();
                navigator.navigate(index);
            }
        } catch (Exception exception) {
            log.error("Ошибка меню");
        }

    }
}
