package org.project.Model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.project.Annotations.GsonExclude;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Guest implements Serializable {

    private String name;
    private String surname;
    private String PhoneNumber;
    private int id;
    private LocalDate localDate;
    @GsonExclude
    private List<Room> rooms;
    @GsonExclude
    private List<Service> services;

    private static final Logger log = LogManager.getLogger(Guest.class);

    public Guest( String name, String surname, String phoneNumber,int id, LocalDate localDate) {
        this.name = name;
        this.surname = surname;
        this.PhoneNumber = phoneNumber;
        this.id = id;
        this.localDate = localDate;
        rooms = new ArrayList<>();
        services = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setArrivalDate(Date date)
    {
        log.info(" Гость прибыл в " + date);
    }

    public void addRoom(Room room){
        rooms.add(room);
    }

    public void addService(Service service){
        services.add(service);
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Service> getServices() {
        return services;
    }

    @Override
    public String toString() {
        return " Гость " + getName() + " " + getSurname() + " уезжает " + getLocalDate();
    }
}
