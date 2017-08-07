package com.epam.javalab.hotelproject.service;

import com.epam.javalab.hotelproject.model.Request;
import com.epam.javalab.hotelproject.model.Room;
import com.epam.javalab.hotelproject.repository.RoomDAO;
import com.epam.javalab.hotelproject.repository.RoomRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RoomServiceImpl implements RoomService {
    private static final RoomComparator roomComparator = new RoomComparator();
    RoomDAO roomDAO = new RoomRepository();

    @Override
    public List<Room> findAll() {
        return roomDAO.findAll();
    }

    @Override
    public Room findByNumber(int number) {
        return roomDAO.findByNumber(number);
    }

    @Override
    public List<Room> getFreeRooms(Request request) {
        return roomDAO.findFreeRooms(request);
    }

    @Override
    public List<Room> findTheMostRelevant(Request request) {
        List<Room> roomList = roomDAO.findFreeRooms(request);
        Collections.sort(roomList, roomComparator);
        return roomList;
    }
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
