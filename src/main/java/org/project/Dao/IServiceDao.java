package org.project.Dao;


import org.project.Model.Service;

import java.util.List;

public interface IServiceDao {
    void addService(Service service);

    void removeService(Service service);

    List<Service> getServices();
}
