package Model;

import java.io.Serializable;
import java.util.Date;

public class Service implements Serializable {
   private String name;
   private double price;
   private String description;
   Date date;


    public Service(String name, double price,String description) {
        this.name = name;
        this.price = price;
        this.description = description;
        date = new Date();
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return getName();
    }
}
