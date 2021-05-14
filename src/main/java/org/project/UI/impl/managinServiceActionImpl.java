package org.project.UI.impl;

import org.project.Controller.IServiceController;
import org.project.UI.IAction;

public class managinServiceActionImpl implements IAction {

    private IServiceController serviceController;
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(managinServiceActionImpl.class);

    public managinServiceActionImpl(IServiceController serviceController) {
        this.serviceController = serviceController;
    }

    @Override
    public void execute() {
        log.info("1. Поменять стоимость на сервис ");
        log.info(" 2 Сортировать услуги по цене  ");
        serviceController.sortServicePrice();
    }
}