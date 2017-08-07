package com.epam.javalab.hotelproject.service;

import com.epam.javalab.hotelproject.model.Room;

import java.util.List;

public interface RoomService {
    List<Room> findAll();

    Room findByNumber(int number);

    Room findTheMostRelevant();
}
