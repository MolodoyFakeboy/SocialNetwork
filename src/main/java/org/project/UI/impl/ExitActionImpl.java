package org.project.UI.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.project.AnnotationHandler.ApplicationContext;
import org.project.UI.IAction;

public class ExitActionImpl implements IAction {

    private ApplicationContext context;
    private static final Logger log = LogManager.getLogger(ExitActionImpl.class);

    public ExitActionImpl(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void execute() {
        try {
            System.exit(0);
        } catch (Exception e) {
            log.error("Ошибка в завершении");
        }
    }
}
