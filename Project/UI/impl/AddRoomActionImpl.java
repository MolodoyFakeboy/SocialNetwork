package UI.impl;

import Controller.RoomController;
import Model.Room;
import UI.IAction;


public class AddRoomActionImpl implements IAction {


    private RoomController roomController;


    public AddRoomActionImpl(RoomController roomController) {
        this.roomController = roomController;
    }

    @Override
    public void execute() {
        Room room = new Room(101, 1, 2, 4, 3000);
        Room room1 = new Room(102, 2, 2, 5, 3500);
        Room room2 = new Room(103, 1, 2, 3, 1500);
        roomController.updateRoom(room);
        roomController.updateRoom(room1);
        roomController.updateRoom(room2);
    }
}
