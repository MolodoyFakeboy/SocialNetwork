package UI.impl;

import Controller.RoomController;
import UI.IAction;

public class sortFreeRoomforPriceActionImpl implements IAction {

    private RoomController roomController;

    public sortFreeRoomforPriceActionImpl(RoomController roomController) {
        this.roomController = roomController;
    }

    @Override
    public void execute() throws Exception {
       roomController.sortFreeRoomforPrice();
    }
}
