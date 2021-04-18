package Dao;

import Annotations.AddArrayList;
import Annotations.Singleton;
import Model.Guest;

import java.io.Serializable;
import java.util.List;

@Singleton
public class GuestDao implements IGuestDao, Serializable {

    @AddArrayList
    private List<Guest> guests;


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
