package Service;

import Dao.IServiceDao;
import Model.Service;

import java.util.stream.Stream;

public interface IFunctionService {
    Service changeServicePrice(Service service, double price);
    Service addService(Service service);
    Stream<Service> sortServicePrice();
    Service getService(int index);
    IServiceDao getServiceDao();

}
