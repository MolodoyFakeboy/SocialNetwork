package src.main.java.UI.impl;


import src.main.java.Controller.IRoomController;
import src.main.java.UI.IAction;

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

