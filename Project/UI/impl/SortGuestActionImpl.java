package UI.impl;

import Controller.GuestController;
import UI.IAction;
import java.util.Scanner;


public class SortGuestActionImpl implements IAction {
    private GuestController guestController;
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(SortGuestActionImpl.class);


    public SortGuestActionImpl(GuestController guestController) {
        this.guestController = guestController;
    }

    @Override
    public void execute(){
        try {
            Scanner in = new Scanner(System.in);
            log.info("Укажите индекс гостя");
            int guestIndex = in.nextInt();
            log.info("1. Получить колличество гостей");
            log.info("2. Сортировать сервисы гостя по цене");
            log.info("3. Сортировать сервисы гостя по дате");
            guestController.sortAction(guestController.getGuest(guestIndex));
        } catch (Exception e){
            log.error("Нет гостя под таким индексом");
        }
    }
}
