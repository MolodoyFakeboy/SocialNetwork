package Dao;

import Model.Guest;


import java.io.Serializable;
import java.util.ArrayList;

public class GuestDao implements IGuestDao, Serializable {

    private ArrayList<Guest> guests;

    public GuestDao() {
        guests = new ArrayList<>();
    }

    @Override
    public void addGuest(Guest guest) {
        guests.add(guest);
    }

    @Override
    public void deletGuest(Guest guest) {
        if (guests.contains(guest)) {
            guests.remove(guest);
        }
    }

    public ArrayList<Guest> getGuests() {
        return guests;
    }
}
