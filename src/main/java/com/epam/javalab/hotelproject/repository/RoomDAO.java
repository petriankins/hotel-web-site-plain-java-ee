package com.epam.javalab.hotelproject.repository;

import com.epam.javalab.hotelproject.model.Request;
import com.epam.javalab.hotelproject.model.Room;

import java.util.Date;
import java.util.List;

public interface RoomDAO {
    List<Room> findAll();

    Room findByNumber(int number);

    Room findByBeds(int beds);

    Room findByClass(int roomClass);

    List<Room> findAvailableRooms(Request request);

    boolean insertRoom(Room room);

    boolean updateRoom(Room room);

    boolean deleteRoom(Room room);
}
