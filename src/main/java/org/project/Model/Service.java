package org.project.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Service implements Serializable {
    @Id
    @Column(name = "idService")
    private int idService;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "price")
    private double price;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "date")
    private Timestamp date;

    @ManyToMany(mappedBy = "services",fetch = FetchType.EAGER)
    private Set<Guest> guests;

    public Service() {

    }

    public Service(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
        date = new Timestamp(System.currentTimeMillis());
        guests = new HashSet<>();
    }

    public Set<Guest> getGuests() {
        return guests;
    }

    public void setGuests(Set<Guest> guests) {
        this.guests = guests;
    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return idService == service.idService &&
                Objects.equals(name, service.name) &&
                Objects.equals(price, service.price) &&
                Objects.equals(description, service.description) &&
                Objects.equals(date, service.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idService, name, price, description, date);
    }

    @Override
    public String toString() {
        return getName() + ":" + " Цена " + getPrice() + " Рублей";
    }

}
