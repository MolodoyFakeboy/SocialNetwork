package org.project.UI.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.project.Controller.IGuestController;
import org.project.Controller.IServiceController;
import org.project.UI.IAction;

import java.util.Scanner;


public class UseServiceActionImpl implements IAction {

    private IGuestController guestController;
    private IServiceController serviceController;
    private static final Logger log = LogManager.getLogger(UseServiceActionImpl.class);

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
//            log.error("Нет такого индекса");
            e.printStackTrace();
        } finally {
            log.info("Попробуйте еще раз!");
        }

    }
}
