package org.project.UI.impl;

import org.project.Controller.IRoomController;
import org.project.Exeception.MyException;
import org.project.UI.IAction;

import java.time.LocalDate;
import java.util.Scanner;

public class CheackRoomIsFreeImpl implements IAction {

    private IRoomController roomController;
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ChangePriceServiceImpl.class);

    public CheackRoomIsFreeImpl(IRoomController roomController) {
        this.roomController = roomController;
    }

    @Override
    public void execute()throws MyException {
        try {
            Scanner in = new Scanner(System.in);
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
            roomController.sortRoomIsFree(localDate);
        } catch (Exception e) {
            log.error("Неверно указан формат даты");
        }

    }
}
