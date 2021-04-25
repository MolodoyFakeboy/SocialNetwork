package src.main.java.UI.impl;


import src.main.java.Controller.IGuestController;
import src.main.java.UI.IAction;

import java.util.Scanner;

public class sortUsingServiceTimeImpl implements IAction {

    private IGuestController guestController;
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(sortUsingServiceTimeImpl.class);

    public sortUsingServiceTimeImpl(IGuestController guestController) {
        this.guestController = guestController;
    }

    @Override
    public void execute() throws Exception {
        try {
            Scanner in = new Scanner(System.in);
            log.info("Укажите индекс гостя");
            int guestIndex = in.nextInt();
            guestController.sortUsingServiceTime(guestController.getGuest(guestIndex));
        } catch (Exception exception) {
            log.error("Неверный индекс");
        }
    }
}
