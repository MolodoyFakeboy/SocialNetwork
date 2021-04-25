package src.main.java.UI.impl;

import src.main.java.Controller.IRoomController;
import src.main.java.UI.IAction;

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
