package com.epam.javalab.hotelproject.repository;

import com.epam.javalab.hotelproject.model.Room;

import java.util.List;

public interface RoomDAO {
    List<Room> finAll();

    Room findByNumber(int number);

    Room findByBeds(int beds);

    Room findByClass(int roomClass);

    boolean insertRoom(Room room);

    boolean updateRoom(Room room);

    boolean deleteRoom(Room room);
}
