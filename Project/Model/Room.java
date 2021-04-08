package Model;

import Resources.EnumStatus;

import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Serializable {

    private int roomID;
    private int roomNumber;
    private int numberOfStars;
    private int floor;
    private EnumStatus status;
    private int numBed;
    private double basePrice;
    ArrayList<Guest> guests;
    ArrayList<Guest> lastGuests;

    public Room(int roomNumber, int numberOfStars, int floor, int numBed, double basePrice) {
        this.roomNumber = roomNumber;
        this.numberOfStars = numberOfStars;
        this.floor = floor;
        this.numBed = numBed;
        this.basePrice = basePrice;
        guests = new ArrayList<>();
        lastGuests = new ArrayList<>();
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(int numberOfStars) {
        this.numberOfStars = numberOfStars;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getNumBed() {
        return numBed;
    }

    public void setNumBed(int numBed) {
        this.numBed = numBed;
    }

    public EnumStatus getStatus() {
        return status;
    }

    public void setStatus(EnumStatus status) {
        this.status = status;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public void addGuestRoom(Guest guest){
        guests.add(guest);
    }

    public void deletGuest(Guest guest){
        guests.remove(guest);
    }

    public void addLastGuest(Guest guest) {
        lastGuests.add(guest);
    }


    public ArrayList<Guest> getGuests() {
        return guests;
    }

    public ArrayList<Guest> getLastGuests() {
        return lastGuests;
    }

    @Override
    public String toString() {
        return  " номер комнаты " + roomNumber + " количество звезд " + numberOfStars + " на этаже " +  floor + " количество кроватей " + numBed + " Цена " + basePrice;
    }
}
