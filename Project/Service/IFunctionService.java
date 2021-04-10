package Service;

import Dao.ServiceDao;
import Model.Service;

import java.util.stream.Stream;

public interface IFunctionService {
    ServiceDao createServiceDao(ServiceDao serviceDao);
    Service changeServicePrice(Service service, double price);
    Service addService(Service service);
    Stream<Service> sortServicePrice();

}
