package UI.impl;

import Controller.RoomController;
import UI.IAction;

public class getAmountFreeRoomActionImpl implements IAction {

    private RoomController roomController;

    public getAmountFreeRoomActionImpl(RoomController roomController) {
        this.roomController = roomController;
    }

    @Override
    public void execute() throws Exception {
       roomController.getAmountFreeRoom();
    }
}
