package Controller;

import Model.Service;
import Service.FunctionService;

public class ServiceController {

    private FunctionService functionService;

    public ServiceController(FunctionService functionService) {
        this.functionService = functionService;
    }

    public Service getService(int index){
       return functionService.getListService().get(index);
    }

    public Service uppdateService(Service service){
        functionService.addService(service);
        return service;
    }

    public void changeServicePrice(int indexService,double price){
        functionService.changeServicePrice(getService(indexService),price);
    }

    public void sortServicePrice(){
        functionService.sortServicePrice();
    }

}
