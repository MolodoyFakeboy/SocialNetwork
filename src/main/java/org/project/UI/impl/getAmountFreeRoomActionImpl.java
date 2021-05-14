package org.project.UI.impl;

import org.project.Controller.IRoomController;
import org.project.UI.IAction;
public class getAmountFreeRoomActionImpl implements IAction {

    private IRoomController roomController;

    public getAmountFreeRoomActionImpl(IRoomController roomController) {
        this.roomController = roomController;
    }

    @Override
    public void execute() throws Exception {
       roomController.getAmountFreeRoom();
    }
}
