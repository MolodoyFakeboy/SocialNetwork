package org.project.Dao;

import org.project.Annotations.AddArrayList;
import org.project.Annotations.Singleton;
import org.project.Model.Guest;

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
