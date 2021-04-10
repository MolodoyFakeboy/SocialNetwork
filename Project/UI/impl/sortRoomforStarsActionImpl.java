package UI.impl;

import Controller.RoomController;
import UI.IAction;

public class sortRoomforStarsActionImpl implements IAction {

    private RoomController roomController;

    public sortRoomforStarsActionImpl(RoomController roomController) {
        this.roomController = roomController;
    }

    @Override
    public void execute() throws Exception {
        roomController.sortRoomforStars();
    }
}
