package org.project.Service;

import org.project.Model.Service;

import java.util.stream.Stream;

public interface IFunctionService {
    Service changeServicePrice(Service service, double price);
    Service addService(Service service);
    Stream<Service> sortServicePrice();
    Service getService(int index);
}
