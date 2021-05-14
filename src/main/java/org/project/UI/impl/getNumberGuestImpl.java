package UI.impl;


import Controller.IGuestController;
import UI.IAction;

public class getNumberGuestImpl implements IAction {

    private IGuestController guestController;
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(getNumberGuestImpl.class);

    public getNumberGuestImpl(IGuestController guestController) {
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
