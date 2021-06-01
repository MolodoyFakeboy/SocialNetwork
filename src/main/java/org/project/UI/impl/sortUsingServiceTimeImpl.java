package org.project.UI.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.project.Controller.IGuestController;
import org.project.UI.IAction;

import java.util.Scanner;

public class sortUsingServiceTimeImpl implements IAction {

    private IGuestController guestController;
    private static final Logger log = LogManager.getLogger(sortUsingServiceTimeImpl.class);
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
