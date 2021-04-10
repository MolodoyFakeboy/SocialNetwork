package Dao;
import Model.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ServiceDao implements IServiceDao, Serializable {
    private List<Service> services;

    public ServiceDao() {
        services = new ArrayList<>();
    }

    public void addService(Service service){
        services.add(service);
    }

    public void removeService(Service service){
        services.remove(service);
    }

    public List<Service> getServices() {
        return services;
    }
}
