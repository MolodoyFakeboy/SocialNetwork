package org.project.Service;

import org.project.Model.Service;

import java.util.List;

public interface IFunctionService {
    Service changeServicePrice(Service service, double price);
    Service addService(Service service);
    List<Service> sortServicePrice();
    Service getService(int index);
}
