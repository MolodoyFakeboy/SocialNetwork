package Dao;

import Model.Guest;

public interface IGuestDao {
    void addGuest(Guest guest);
    void deletGuest(Guest guest);
}
