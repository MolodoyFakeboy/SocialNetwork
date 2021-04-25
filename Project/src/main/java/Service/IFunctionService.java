package src.main.java.Service;


import src.main.java.Dao.IServiceDao;
import src.main.java.Model.Service;

import java.util.stream.Stream;

public interface IFunctionService {
    Service changeServicePrice(Service service, double price);
    Service addService(Service service);
    Stream<Service> sortServicePrice();
    Service getService(int index);
    IServiceDao getServiceDao();

}
