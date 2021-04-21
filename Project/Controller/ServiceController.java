package Controller;

import Dao.IServiceDao;
import Model.Service;
import Service.IFunctionService;

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
