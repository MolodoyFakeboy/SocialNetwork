package org.project.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Service implements Serializable {
    private int idService;
    private String name;
    private double price;
    private String description;
    private Timestamp date;
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

    @ManyToMany(mappedBy = "services",fetch = FetchType.EAGER)
    public Set<Guest> getGuests() {
        return guests;
    }

    public void setGuests(Set<Guest> guests) {
        this.guests = guests;
    }

    @Id
    @Column(name = "idService")
    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
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
    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "date")
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
