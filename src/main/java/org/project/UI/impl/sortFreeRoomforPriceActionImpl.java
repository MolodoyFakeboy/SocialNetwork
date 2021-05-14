package org.project.UI.impl;

import org.project.Controller.IRoomController;
import org.project.UI.IAction;

public class sortFreeRoomforPriceActionImpl implements IAction {

    private IRoomController roomController;

    public sortFreeRoomforPriceActionImpl(IRoomController roomController) {
        this.roomController = roomController;
    }

    @Override
    public void execute() throws Exception {
       roomController.sortFreeRoomforPrice();
    }
}
