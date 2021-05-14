package org.project.UI.impl;

import org.project.Controller.IRoomController;
import org.project.UI.IAction;

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
