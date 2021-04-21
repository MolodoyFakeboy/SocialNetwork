package UI.impl;

import Controller.IRoomController;
import UI.IAction;

public class sortFreeRoomStarsActionImpl implements IAction {

    private IRoomController roomController;

    public sortFreeRoomStarsActionImpl(IRoomController roomController) {
        this.roomController = roomController;
    }

    @Override
    public void execute() throws Exception {
        roomController.sortFreeRoomStars();
    }
}
