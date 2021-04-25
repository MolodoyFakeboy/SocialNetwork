package src.main.java.Dao;


import src.main.java.Model.Service;

import java.util.List;

public interface IServiceDao {
    void addService(Service service);
    void removeService(Service service);
    List<Service> getServices();
}
