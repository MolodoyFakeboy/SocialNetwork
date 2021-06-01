package org.project.UI.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.project.Controller.IGuestController;
import org.project.UI.IAction;

public class getNumberGuestImpl implements IAction {

    private IGuestController guestController;
    private static final Logger log = LogManager.getLogger(getNumberGuestImpl.class);
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
