package org.project.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "surname")
    private String surname;
    @Basic
    @Column(name = "PhoneNumber")
    private String phoneNumber;
    @Basic
    @Column(name = "LocalDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date localDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "room_has_guest",
            joinColumns = {@JoinColumn(name = "Guest_id")},
            inverseJoinColumns = {@JoinColumn(name = "Room_roomID")}
    )
    @JsonIgnore
    private Set<Room> rooms;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "guest_has_service",
            joinColumns = @JoinColumn(name = "Guest_id"),
            inverseJoinColumns = @JoinColumn(name = "Service_idService"))
    @JsonIgnore
    private Set<Service> services;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "lastguests_room",
            joinColumns = {@JoinColumn(name = "Guest_id")},
            inverseJoinColumns = {@JoinColumn(name = "Room_roomID")}
    )
    @JsonIgnore
    private Set<Room> lastRooms;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getLocalDate() {
        return localDate;
    }

    public void setLocalDate(Date localDate) {
        this.localDate = localDate;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }

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
