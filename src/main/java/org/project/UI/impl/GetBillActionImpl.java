package org.project.UI.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.project.Controller.IGuestController;
import org.project.Controller.IRoomController;
import org.project.UI.IAction;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class GetBillActionImpl implements IAction {
    private IGuestController guestController;
    private IRoomController roomController;

    private static final Logger log = LogManager.getLogger(GetBillActionImpl.class);

    public GetBillActionImpl(IGuestController guestController, IRoomController roomController) {
        this.guestController = guestController;
        this.roomController = roomController;
    }

    @Override
    public void execute(){
        try {
            Scanner in = new Scanner(System.in);
            log.info("Укажите индекс вашей комнаты");
            int indexRoom = in.nextInt();
            guestController.getaBill(indexRoom);
        } catch (NoSuchElementException exception){
            log.error("нет елемента под таким индексом");
        } catch (Exception e){
            log.error("Неверно указанный индекс");
        }

    }
}
