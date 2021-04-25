package src.main.java.Controller;




import src.main.java.UI.MenuBuilder;
import src.main.java.UI.Navigator;

import java.util.Scanner;


public class MenuController {

    private final MenuBuilder builder;
    private final Navigator navigator;

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MenuController.class);


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
