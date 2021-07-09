package org.project.Controller;

import org.project.Model.Service;

import java.util.List;

public interface IServiceController {

    Service getService(int index);

    Service uppdateService(Service service);

    Service changeServicePrice(Service service);

    List<Service> sortServicePrice();
}
