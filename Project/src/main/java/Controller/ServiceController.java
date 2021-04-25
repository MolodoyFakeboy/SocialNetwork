package src.main.java.Controller;

import src.main.java.Annotations.Singleton;
import src.main.java.Dao.IServiceDao;
import src.main.java.Model.Service;
import src.main.java.Service.IFunctionService;


@Singleton
public class ServiceController implements IServiceController {


    private IFunctionService functionService;

    public ServiceController(IFunctionService functionService) {
        this.functionService = functionService;
    }

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

    @Override
    public IServiceDao getServiceDao() {
        return functionService.getServiceDao();
    }

}
