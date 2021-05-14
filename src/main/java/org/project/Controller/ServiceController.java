package Controller;

import Annotations.InjectByType;
import Annotations.Singleton;
import Model.Service;
import Service.IFunctionService;


@Singleton
public class ServiceController implements IServiceController {

    @InjectByType
    private IFunctionService functionService;

    @Override
    public Service getService(int index){
       return functionService.getService(index);

    }

    @Override
    public Service uppdateService(Service service){
        functionService.addService(service);
        return service;
    }

    @Override
    public void changeServicePrice(int indexService,double price){
        functionService.changeServicePrice(getService(indexService),price);
    }

    @Override
    public void sortServicePrice(){
        functionService.sortServicePrice();
    }

}
