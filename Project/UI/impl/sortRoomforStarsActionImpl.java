package UI.impl;

import Controller.IRoomController;
import UI.IAction;

public class sortRoomforStarsActionImpl implements IAction {

    private IRoomController roomController;

    public sortRoomforStarsActionImpl(IRoomController roomController) {
        this.roomController = roomController;
    }

    @Override
    public void execute() throws Exception {
        roomController.sortRoomforStars();
    }
}
