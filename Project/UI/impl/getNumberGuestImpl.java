package UI.impl;

import Controller.GuestController;
import UI.IAction;

public class getNumberGuestImpl implements IAction {

    private GuestController guestController;
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(getNumberGuestImpl.class);

    public getNumberGuestImpl(GuestController guestController) {
        this.guestController = guestController;
    }

    @Override
    public void execute() throws Exception {
        try {
            guestController.getNumberGuest();
        } catch (Exception exception) {
            log.error("Неверный формат");
        }
    }
}
