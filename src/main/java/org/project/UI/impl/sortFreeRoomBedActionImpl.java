package org.project.UI.impl;

import org.project.Controller.IRoomController;
import org.project.UI.IAction;

public class sortFreeRoomBedActionImpl implements IAction {

    private IRoomController roomController;
    public sortFreeRoomBedActionImpl(IRoomController roomController) {
        this.roomController = roomController;
    }
    @Override
    public void execute() throws Exception {
       roomController.sortFreeRoomBed();
    }
}
