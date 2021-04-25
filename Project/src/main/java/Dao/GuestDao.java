package src.main.java.Dao;

import src.main.java.Annotations.AddArrayList;
import src.main.java.Annotations.Singleton;
import src.main.java.Model.Guest;

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

    @Override
    public List<Guest> getGuests() {
        return guests;
    }
}
