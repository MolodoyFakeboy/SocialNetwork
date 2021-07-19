package org.project.Controller;

import org.project.Exeception.IdIncorrectData;
import org.project.Model.Service;
import org.project.Service.IFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class ServiceController implements IServiceController {

    private IFunctionService functionService;

    @Autowired
    public ServiceController(IFunctionService functionService) {
        this.functionService = functionService;
    }

    @Override
    @GetMapping("service/{index}")
    public Service getService(@PathVariable int index) {
        Service service = functionService.getService(index);
        if (service == null) {
            throw new NoSuchElementException("There is no service with such id = " + index + " in shema hotel");
        }
        return service;
    }

    @Override
    @PostMapping("service")
    public Service uppdateService(@RequestBody Service service) {
        functionService.addService(service);
        return service;
    }

    @Override
    @PutMapping("service")
    public Service changeServicePrice(@RequestBody Service service) {
        functionService.addService(service);
        return service;
    }

    @Override
    @GetMapping("services")
    public List<Service> sortServicePrice() {
        return functionService.sortServicePrice();
    }

    @ExceptionHandler
    public ResponseEntity<IdIncorrectData> handleException(NoSuchElementException exception){
        IdIncorrectData idIncorrectData = new IdIncorrectData();
        idIncorrectData.setInfo(exception.getMessage());
        return new ResponseEntity<>(idIncorrectData, HttpStatus.NOT_FOUND);
    }

}
