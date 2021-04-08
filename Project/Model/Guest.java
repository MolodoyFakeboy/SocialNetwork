package Model;

import org.apache.log4j.Logger;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Guest implements Serializable {

    private String name;
    private String surname;
    private String PhoneNumber;
    private int id;
    private LocalDate localDate;
    ArrayList<Room> rooms;
    ArrayList<Service> services;

    private static final Logger log = Logger.getLogger(Guest.class);

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

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    @Override
    public String toString() {
        return " Гость " + getName() + " " + getSurname() + " уезжает " + getLocalDate();
    }
}
