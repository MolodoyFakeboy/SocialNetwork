package src.main.java.UI.impl;


import src.main.java.Controller.IRoomController;
import src.main.java.Model.Room;
import src.main.java.UI.IAction;

public class AddRoomActionImpl implements IAction {


    private IRoomController roomController;


    public AddRoomActionImpl(IRoomController roomController) {
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
