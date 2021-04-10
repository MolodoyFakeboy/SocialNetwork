package Dao;

import Model.Guest;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GuestDao implements IGuestDao, Serializable {

    private List<Guest> guests;

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
    public List<Guest> getGuests() {
        return guests;
    }
}
