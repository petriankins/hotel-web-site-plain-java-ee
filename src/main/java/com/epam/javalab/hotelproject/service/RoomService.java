package com.epam.javalab.hotelproject.service;

import com.epam.javalab.hotelproject.model.Room;

import java.util.Comparator;
import java.util.List;

public interface RoomService {
    List<Room> findAll();

    Room findByNumber(int number);

    Room findTheMostRelevant();
}

class RoomComparator implements Comparator<Room> {
    @Override
    public int compare(Room o1, Room o2) {
        int result = o1.getBeds() - o2.getBeds();
        if (result == 0) {
            return o1.getRoomClass() - o2.getRoomClass();
        }
        return result;
    }
}