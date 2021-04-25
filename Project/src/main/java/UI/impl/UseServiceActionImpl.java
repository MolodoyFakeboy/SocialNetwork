package src.main.java.UI.impl;



import src.main.java.Controller.IGuestController;
import src.main.java.Controller.IServiceController;
import src.main.java.UI.IAction;

import java.util.Scanner;


public class UseServiceActionImpl implements IAction {

    private IGuestController guestController;
    private IServiceController serviceController;
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(UseServiceActionImpl.class);


    public UseServiceActionImpl(IGuestController guestController, IServiceController serviceController) {
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
