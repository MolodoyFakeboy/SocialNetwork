package org.project.Controller;

import org.project.Model.Service;

public interface IServiceController {
    Service getService(int index);

    Service uppdateService(Service service);

    void changeServicePrice(int indexService, double price);

    void sortServicePrice();
}
