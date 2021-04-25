package src.main.java.UI.impl;


import src.main.java.Controller.IRoomController;
import src.main.java.UI.IAction;

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
