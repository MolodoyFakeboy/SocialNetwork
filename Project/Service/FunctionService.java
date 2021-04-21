package Service;

import Annotations.InjectByType;
import Annotations.Singleton;
import Dao.ServiceDao;
import Model.Service;
import org.apache.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;


@Singleton
public class FunctionService implements IFunctionService {

    @InjectByType
    private ServiceDao serviceDao;

    private Logger log;


    public FunctionService() {
        log = Logger.getLogger(ServiceDao.class);
    }

    public void setServiceDao(ServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }

    @Override
    public ServiceDao createServiceDao(ServiceDao serviceDao){
        this.serviceDao = serviceDao;
        return serviceDao;
    }

    @Override
    public Service changeServicePrice(Service service, double price) {
        service.setPrice(price);
        log.info(" Цена на услугу " + service.getName() + " составляет  " + price);
        return service;
    }

    @Override
    public Service addService(Service service) {
        serviceDao.addService(service);
        log.info("Добавлена новая услуга" + service.getName());
        return service;
    }

    @Override
    public Stream<Service> sortServicePrice() {
        Stream<Service> stream = serviceDao.getServices().stream();
        stream.sorted(Comparator.comparing(Service::getPrice)).forEach(service -> log.info(" Цены на услуги составляю " + service.getName() + " " + service.getPrice()));
        return stream;
    }

    public List<Service> getListService(){
        return serviceDao.getServices();
    }

    public ServiceDao getServiceDao() {
        return serviceDao;
    }
}
