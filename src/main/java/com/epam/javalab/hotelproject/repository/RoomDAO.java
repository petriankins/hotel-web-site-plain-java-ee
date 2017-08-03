package com.epam.javalab.hotelproject.repository;

import com.epam.javalab.hotelproject.model.Room;

import java.util.List;

public interface RoomDAO {
    List<Room> finAll();
    Room findByNumber();
    Room findByBeds();
    Room findByClass();
    Room findAvailable();
    boolean insertRoom();
    boolean updateRoom();
    boolean deleteRoom();
}
