package Controller;

import Dao.IServiceDao;
import Model.Service;

public interface IServiceController {
    Service getService(int index);
    Service uppdateService(Service service);
    void changeServicePrice(int indexService,double price);
    void sortServicePrice();
    IServiceDao getServiceDao();
}
