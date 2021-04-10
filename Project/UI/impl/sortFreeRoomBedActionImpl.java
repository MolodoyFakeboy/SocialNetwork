package UI.impl;

import Controller.RoomController;
import UI.IAction;

public class sortFreeRoomBedActionImpl implements IAction {

    private RoomController roomController;

    public sortFreeRoomBedActionImpl(RoomController roomController) {
        this.roomController = roomController;
    }

    @Override
    public void execute() throws Exception {
       roomController.sortFreeRoomBed();
    }
}
