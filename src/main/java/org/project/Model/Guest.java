package org.project.Model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Guest implements Serializable {
    private int id;
    private String name;
    private String surname;
    private String phoneNumber;
    private Date localDate;
    private Set<Room> rooms;
    private Set<Service> services;
    private Set<Room>lastRooms;

    private static final Logger log = LogManager.getLogger(Guest.class);

    public Guest() {

    }

    public Guest(String name, String surname, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        rooms = new HashSet<>();
        services = new HashSet<>();
        lastRooms = new HashSet<>();
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
    public Date getLocalDate() {
        return localDate;
    }

    public void setLocalDate(Date localDate) {
        this.localDate = localDate;
    }

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "guest_has_service",
            joinColumns = @JoinColumn(name = "Guest_id"),
            inverseJoinColumns = @JoinColumn(name = "Service_idService"))
    public Set<Service> getServices() {
        return services;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "lastguests_room",
            joinColumns = { @JoinColumn(name = "Guest_id")},
            inverseJoinColumns = { @JoinColumn(name = "Room_roomID")}
    )
    public Set<Room> getLastRooms() {
        return lastRooms;
    }

    public void setLastRooms(Set<Room> lastRooms) {
        this.lastRooms = lastRooms;
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
        return " Гость " + getName() + " " + getSurname();
    }
}
