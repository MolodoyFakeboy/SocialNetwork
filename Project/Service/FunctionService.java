package Service;

import Dao.ServiceDao;
import Model.Service;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Stream;


public class FunctionService implements IFunctionService {

     private ServiceDao serviceDao;

    public FunctionService(ServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }
    private static final Logger log = Logger.getLogger(ServiceDao.class);

    @Override
    public ServiceDao createServiceDao(ServiceDao serviceDao){
        this.serviceDao = new ServiceDao();
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

    public ArrayList<Service> getListService(){
        return serviceDao.getServices();
    }

}
