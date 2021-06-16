package org.project.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
public class Room implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomId;
    private int roomNumber;
    private int numberOfStars;
    private int floor;
    private EnumStatus status;
    private int numBed;
    private double basePrice;
    private Set<Guest> guests;
    private Set<Guest> lastGuests;


    public Room() {

    }

    public Room(int roomNumber, int numberOfStars, int floor, int numBed, double basePrice) {
        this.roomNumber = roomNumber;
        this.numberOfStars = numberOfStars;
        this.floor = floor;
        this.numBed = numBed;
        this.basePrice = basePrice;
        guests = new HashSet<>();
        lastGuests = new HashSet<>();
    }

    public void setGuests(Set<Guest> guests) {
        this.guests = guests;
    }

    @Id
    @Column(name = "roomID")
    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    @Basic
    @Column(name = "roomNumber")
    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Basic
    @Column(name = "numberOfStars")
    public int getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(int numberOfStars) {
        this.numberOfStars = numberOfStars;
    }

    @Basic
    @Column(name = "floor")
    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    @Basic
    @Column(name = "status")
    public EnumStatus getStatus() {
        return status;
    }

    public void setStatus(EnumStatus status) {
        this.status = status;
    }

    @Basic
    @Column(name = "numBed")
    public int getNumBed() {
        return numBed;
    }

    public void setNumBed(int numBed) {
        this.numBed = numBed;
    }

    @Basic
    @Column(name = "basePrice")
    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return roomId == room.roomId &&
                roomNumber == room.roomNumber &&
                numberOfStars == room.numberOfStars &&
                floor == room.floor &&
                numBed == room.numBed &&
                Objects.equals(status, room.status) &&
                Objects.equals(basePrice, room.basePrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, roomNumber, numberOfStars, floor, status, numBed, basePrice);
    }

    @ManyToMany(mappedBy = "rooms", fetch = FetchType.EAGER)
    public Set<Guest> getGuests() {
        return guests;
    }

    @ManyToMany(mappedBy = "lastRooms",fetch = FetchType.EAGER)
    public Set<Guest> getLastGuests() {
        return lastGuests;
    }

    public void setLastGuests(Set<Guest> lastGuests) {
        this.lastGuests = lastGuests;
    }

    @Override
    public String toString() {
        return " номер комнаты " + roomNumber + " количество звезд " + numberOfStars + " на этаже " + floor + " количество кроватей " + numBed + " Цена " + basePrice;
    }

}
