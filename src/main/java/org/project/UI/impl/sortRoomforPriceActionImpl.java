package UI.impl;


import Controller.IRoomController;
import UI.IAction;

public class sortRoomforPriceActionImpl implements IAction {

    private IRoomController roomController;

    public sortRoomforPriceActionImpl(IRoomController roomController) {
        this.roomController = roomController;
    }

    @Override
    public void execute() throws Exception {
       roomController.sortRoomforPrice();
    }
}
