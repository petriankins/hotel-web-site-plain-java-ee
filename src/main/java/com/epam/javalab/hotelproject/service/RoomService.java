package com.epam.javalab.hotelproject.service;

import com.epam.javalab.hotelproject.model.Request;
import com.epam.javalab.hotelproject.model.Room;

import java.util.List;

public interface RoomService {
    List<Room> findAll();

    Room findByNumber(int number);

    List<Room> getFreeRooms(Request request);

    List<Room> findTheMostRelevant(Request request);
}

