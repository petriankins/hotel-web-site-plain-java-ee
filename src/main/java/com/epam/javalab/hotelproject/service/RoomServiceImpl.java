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
        return roomDAO.findAvailableRooms(request);
    }

    @Override
    public List<Room> findTheMostRelevant(Request request) {
        List<Room> roomList = roomDAO.findAvailableRooms(request);
        Collections.sort(roomList, roomComparator);
        return roomList;
    }
}

class RoomComparator implements Comparator<Room> {
    @Override
    public int compare(Room o1, Room o2) {
        int result;
        int bedsSubstraction = o1.getBeds() - o2.getBeds();
        int roomClassSubstraction = o1.getRoomClass() - o2.getRoomClass();
        if (bedsSubstraction == 0 && roomClassSubstraction == 0) {
            return 0;
        } else if (bedsSubstraction == 0 && roomClassSubstraction > 0) {
            result = roomClassSubstraction;
            return result;
        } else if (bedsSubstraction > 0 && roomClassSubstraction == 0) {
            result = bedsSubstraction;
            return result;
        }
        result = bedsSubstraction;
        return result;
    }
}
