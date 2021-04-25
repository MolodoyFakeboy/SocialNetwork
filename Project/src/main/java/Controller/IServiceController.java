package src.main.java.Controller;


import src.main.java.Dao.IServiceDao;
import src.main.java.Model.Service;

public interface IServiceController {
    Service getService(int index);
    Service uppdateService(Service service);
    void changeServicePrice(int indexService,double price);
    void sortServicePrice();
    IServiceDao getServiceDao();
}
