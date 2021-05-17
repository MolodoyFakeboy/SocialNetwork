package org.project.Service;

import org.project.Annotations.InjectByType;
import org.project.Annotations.Singleton;
import org.project.Dao.IServiceDao;
import org.project.Dao.ServiceDao;
import org.project.Model.Service;
import org.apache.log4j.Logger;
import java.util.Comparator;
import java.util.stream.Stream;

@Singleton
public class FunctionService implements IFunctionService {

    @InjectByType
    private IServiceDao serviceDao;

    private Logger log;

    public FunctionService() {
        log = Logger.getLogger(ServiceDao.class);
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

    @Override
    public Service getService(int index) {
        return serviceDao.getServices().get(index);
    }


}
