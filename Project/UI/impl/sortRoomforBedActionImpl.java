package UI.impl;

import Controller.IRoomController;
import UI.IAction;

public class sortRoomforBedActionImpl implements IAction {

    private IRoomController roomController;

    public sortRoomforBedActionImpl(IRoomController roomController) {
        this.roomController = roomController;
    }

    @Override
    public void execute() throws Exception {
        roomController.sortRoomforBed();
    }
}

