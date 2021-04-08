package UI.impl;

import Controller.GuestController;
import Controller.RoomController;
import Model.Guest;
import Service.RoomService;
import UI.IAction;
import Resources.MyException;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Logger;

public class BookRoomActionImpl implements IAction {

    private final GuestController guestController;
    private final RoomController roomController;
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(BookRoomActionImpl.class);


    public BookRoomActionImpl(GuestController guestController, RoomController roomController) {
        this.guestController = guestController;
        this.roomController = roomController;
    }

    @Override
    public void execute() throws MyException {
        Scanner in = new Scanner(System.in);
        try {
            log.info("Укажите индекс комнаты в которой хотите поселиться");
            int index = in.nextInt();
            log.info("Укажите данные о себе");
            log.info("Напишите ваше имя");
            String name = in.nextLine();
            in.nextLine();
            log.info("Напишите вашу фамилию");
            String surname = in.nextLine();
            if(surname.equals("")) {
                throw new MyException("String can not be empty!");
            }
            log.info("Напишите ваш номер телефона");
            String phoneNumber = in.nextLine();
            log.info("Укажите ваш айди");
            int id = in.nextInt();
            LocalDate date = LocalDate.of(2021, 3, 27);
            Guest guest = new Guest(name, surname, phoneNumber, id, date);
            guestController.bookRoom(roomController.getRoom(index), guest);
        } catch (IndexOutOfBoundsException e) {
            log.error("Нет номера под таким индексом");
        }

    }
}
