package UI.impl;

import Controller.GuestController;
import UI.IAction;

import java.util.Scanner;

public class sortUsingServicePriceImpl implements IAction {

    private final GuestController guestController;
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(sortUsingServicePriceImpl.class);

    public sortUsingServicePriceImpl(GuestController guestController) {
        this.guestController = guestController;
    }

    @Override
    public void execute() throws Exception {
        try {
            Scanner in = new Scanner(System.in);
            log.info("Укажите индекс гостя");
            int guestIndex = in.nextInt();
            guestController.sortUsingServicePrice(guestController.getGuest(guestIndex));
        } catch (Exception exception) {
            log.error("Неверный индекс");
        }
    }
}
