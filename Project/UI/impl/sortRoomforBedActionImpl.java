package UI.impl;

import Controller.RoomController;
import UI.IAction;

public class sortRoomforBedActionImpl implements IAction {

    private RoomController roomController;

    public sortRoomforBedActionImpl(RoomController roomController) {
        this.roomController = roomController;
    }

    @Override
    public void execute() throws Exception {
        roomController.sortRoomforBed();
    }
}

