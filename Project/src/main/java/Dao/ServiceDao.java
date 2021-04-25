package src.main.java.Dao;


import src.main.java.Annotations.AddArrayList;
import src.main.java.Annotations.Singleton;
import src.main.java.Model.Service;

import java.io.Serializable;
import java.util.List;


@Singleton
public class ServiceDao implements IServiceDao, Serializable {

    @AddArrayList
    private List<Service> services;

    @Override
    public void addService(Service service){
        services.add(service);
    }

    @Override
    public void removeService(Service service){
        services.remove(service);
    }

    @Override
    public List<Service> getServices() {
        return services;
    }
}
