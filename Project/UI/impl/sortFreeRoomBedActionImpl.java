package UI.impl;

import Controller.IRoomController;
import UI.IAction;

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
