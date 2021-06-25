package org.project.Controller;

import org.project.Annotations.InjectByType;
import org.project.Annotations.Singleton;
import org.project.Model.Service;
import org.project.Service.IFunctionService;

@Singleton
public class ServiceController implements IServiceController {

    @InjectByType
    private IFunctionService functionService;

    @Override
    public Service getService(int index) {
        return functionService.getService(index);

    }

    @Override
    public Service uppdateService(Service service) {
        functionService.addService(service);
        return service;
    }

    @Override
    public void changeServicePrice(int indexService, double price) {
        functionService.changeServicePrice(getService(indexService), price);
    }

    @Override
    public void sortServicePrice() {
        functionService.sortServicePrice();
    }

}
