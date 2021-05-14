package UI.impl;


import Controller.IRoomController;
import UI.IAction;

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
