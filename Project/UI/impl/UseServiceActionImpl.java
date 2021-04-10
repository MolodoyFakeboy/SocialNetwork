package UI.impl;

import Controller.GuestController;
import Controller.ServiceController;
import UI.IAction;
import java.util.Scanner;


public class UseServiceActionImpl implements IAction {

    private GuestController guestController;
    private ServiceController serviceController;
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(UseServiceActionImpl.class);


    public UseServiceActionImpl(GuestController guestController, ServiceController serviceController) {
        this.guestController = guestController;
        this.serviceController = serviceController;
    }

    @Override
    public void execute() {
        try{
            Scanner in = new Scanner(System.in);
            log.info("Укажите индекс cервиса которым хотите воспользоваться");
            int index = in.nextInt();
            log.info("Укажите ваш индекс");
            int guestIndex = in.nextInt();
            guestController.useService(serviceController.getService(index), guestIndex);
        }catch (Exception e){
            log.error("Нет такого индекса");
        } finally {
            log.info("Попробуйте еще раз!");
        }

    }
}
