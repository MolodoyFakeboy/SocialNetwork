package org.project.UI.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.project.Controller.IGuestController;
import org.project.Controller.IRoomController;
import org.project.Exeception.MyException;
import org.project.Model.Guest;
import org.project.UI.IAction;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Scanner;

public class BookRoomActionImpl implements IAction {

    private final IGuestController guestController;
    private final IRoomController roomController;
    private static final Logger log = LogManager.getLogger(BookRoomActionImpl.class);

    public BookRoomActionImpl(IGuestController guestController, IRoomController roomController) {
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
            in.nextLine();
            String name = in.nextLine();
            log.info("Напишите вашу фамилию");
            String surname = in.nextLine();
            if(surname.equals("")) {
                throw new MyException("String can not be empty!");
            }
            log.info("Напишите ваш номер телефона");
            String phoneNumber = in.nextLine();
            Guest guest = new Guest(name, surname, phoneNumber);
            log.info("Напишите дату вашего отъезда");
            log.info("Укажите день");
            int day = in.nextInt();
            if (day == 0){
                throw new MyException("String can not be empty!");
            }
            log.info("Укажите месяц");
            int month = in.nextInt();
            if (month == 0){
                throw new MyException("String can not be empty!");
            }
            log.info("Укажите год");
            int year = in.nextInt();
            if (year == 0){
                throw new MyException("String can not be empty!");
            }
            LocalDate localDate = LocalDate.of(year, month, day);
            Timestamp timestamp = Timestamp.valueOf(localDate.toString());
            guestController.bookRoom(roomController.getRoom(index), guest,timestamp);
        } catch (IndexOutOfBoundsException e) {
            log.error("Нет номера под таким индексом");
        }

    }
}
