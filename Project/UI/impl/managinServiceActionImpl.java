package UI.impl;

import Controller.ServiceController;
import UI.IAction;

import java.time.LocalDate;
import java.util.Scanner;

public class managinServiceActionImpl implements IAction {

    private ServiceController serviceController;
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(managinServiceActionImpl.class);

    public managinServiceActionImpl(ServiceController serviceController) {
        this.serviceController = serviceController;
    }

    @Override
    public void execute() {
        log.info("1. Поменять стоимость на сервис ");
        log.info(" 2 Сортировать услуги по цене  ");
        serviceController.sortServicePrice();
    }
}