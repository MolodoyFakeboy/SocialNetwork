package org.project.Controller;

import org.project.Model.Service;
import org.project.Service.IFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ServiceController implements IServiceController {

    private IFunctionService functionService;

    @Autowired
    public ServiceController(IFunctionService functionService) {
        this.functionService = functionService;
    }

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
