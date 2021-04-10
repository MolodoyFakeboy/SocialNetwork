package UI.impl;

import Controller.RoomController;
import UI.IAction;

public class sortRoomforPriceActionImpl implements IAction {

    private RoomController roomController;

    public sortRoomforPriceActionImpl(RoomController roomController) {
        this.roomController = roomController;
    }

    @Override
    public void execute() throws Exception {
       roomController.sortRoomforPrice();
    }
}
