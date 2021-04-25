package src.main.java.Dao;


import src.main.java.Model.Guest;

import java.util.List;

public interface IGuestDao {
    void addGuest(Guest guest);
    void deletGuest(Guest guest);
    List<Guest> getGuests();
}
