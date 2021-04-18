package Dao;
import Annotations.AddArrayList;
import Annotations.Singleton;
import Model.Service;

import java.io.Serializable;
import java.util.List;

@Singleton
public class ServiceDao implements IServiceDao, Serializable {

    @AddArrayList
    private List<Service> services;


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
