package UI.impl;


import Controller.IServiceController;
import Model.Service;
import UI.IAction;

public class AddServiceActionImpl implements IAction {


    private IServiceController serviceController;

    public AddServiceActionImpl(IServiceController serviceController) {
        this.serviceController = serviceController;
    }

    @Override
    public void execute() {
        Service service = new Service("Кофе в номер",200,"принесем кофе прямо вам в номер");
        Service service1 = new Service("Напитки в мини бар",800,"Принесем  для вас самые вкусные напитки");
        serviceController.uppdateService(service);
        serviceController.uppdateService(service1);
    }
}
