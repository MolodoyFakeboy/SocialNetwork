package UI.impl;

import Controller.RoomController;
import UI.IAction;

public class sortFreeRoomStarsActionImpl implements IAction {

    private RoomController roomController;

    public sortFreeRoomStarsActionImpl(RoomController roomController) {
        this.roomController = roomController;
    }

    @Override
    public void execute() throws Exception {
        roomController.sortFreeRoomStars();
    }
}
