package org.project.Model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Guest implements Serializable {
    private int id;
    private String name;
    private String surname;
    private String phoneNumber;
    private Timestamp localDate;
    private Set<Room> rooms;
    private Set<Service> services;

    private static final Logger log = LogManager.getLogger(Guest.class);

    public Guest() {

    }

    public Guest(String name, String surname, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.localDate = new Timestamp(System.currentTimeMillis());
        rooms = new HashSet<>();
        services = new HashSet<>();
    }


    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "PhoneNumber")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "LocalDate")
    public Timestamp getLocalDate() {
        return localDate;
    }

    public void setLocalDate(Timestamp localDate) {
        this.localDate = localDate;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "room_has_guest",
            joinColumns = {@JoinColumn(name = "Guest_id")},
            inverseJoinColumns = {@JoinColumn(name = "Room_roomID")}
    )
    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }

    @ManyToMany
    @JoinTable(name = "guest_has_service",
            joinColumns = @JoinColumn(name = "Guest_id"),
            inverseJoinColumns = @JoinColumn(name = "Service_idService"))
    public Set<Service> getServices() {
        return services;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guest guest = (Guest) o;
        return id == guest.id &&
                Objects.equals(name, guest.name) &&
                Objects.equals(surname, guest.surname) &&
                Objects.equals(phoneNumber, guest.phoneNumber) &&
                Objects.equals(localDate, guest.localDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, phoneNumber, localDate);
    }

    @Override
    public String toString() {
        return " Гость " + getName() + " " + getSurname() + " уезжает " + getLocalDate();
    }
}
