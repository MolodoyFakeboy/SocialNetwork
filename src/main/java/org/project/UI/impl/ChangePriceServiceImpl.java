package org.project.UI.impl;

import org.project.Controller.IServiceController;
import org.project.UI.IAction;

import java.util.Scanner;

public class ChangePriceServiceImpl implements IAction {
    private IServiceController serviceController;
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ChangePriceServiceImpl.class);

    public ChangePriceServiceImpl(IServiceController serviceController) {
        this.serviceController = serviceController;
    }

    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        try {
            log.info("Укажите индекс cервиса");
            int index = in.nextInt();
            log.info("Укажите цену которую хотите поставить");
            double price = in.nextInt();
            serviceController.changeServicePrice(index,price);
        } catch (Exception e){
            log.error("Нет сервиса под таким индексом");
        }

    }
}
