package Controller;

import Annotations.InjectByType;
import Annotations.Singleton;
import Dao.ServiceDao;
import Model.Service;
import Service.FunctionService;

@Singleton
public class ServiceController {

    @InjectByType
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

    public ServiceDao getServiceDao() {
        return functionService.getServiceDao();
    }
}
