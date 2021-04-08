package Dao;
import Model.Service;

import java.io.Serializable;
import java.util.ArrayList;

public class ServiceDao implements IServiceDao, Serializable {
    private ArrayList<Service>services;

    public ServiceDao() {
        services = new ArrayList<>();
    }

    public void addService(Service service){
        services.add(service);
    }

    public void removeService(Service service){
        services.remove(service);
    }

    public ArrayList<Service> getServices() {
        return services;
    }
}
