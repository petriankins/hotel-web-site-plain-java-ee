package com.epam.javalab.hotelproject.service;

import com.epam.javalab.hotelproject.model.Request;
import com.epam.javalab.hotelproject.model.Room;
import com.epam.javalab.hotelproject.repository.RoomDAO;
import com.epam.javalab.hotelproject.repository.RoomRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Provides API for working with rooms.
 *
 * @author Iaichnikov Denis,
 * @version 1.0
 * @since 1.0
 */

public class RoomServiceImpl implements RoomService {
    private static final RoomComparator roomComparator = new RoomComparator();
    private final RoomDAO roomDAO = new RoomRepository();

    @Override
    public List<Room> findAll() {
        return roomDAO.findAll();
    }

    @Override
    public Room findByNumber(int number) {
        return roomDAO.findByNumber(number);
    }

    @Override
    public List<Room> getAvailableRooms(Request request) {
        return roomDAO.findAvailableRooms(request);
    }

    /**
     * Finds the most relevant to the request room list. First, it finds rooms with equal or greater quantity
     * of beds than in request. Then comparator sorts the obtained results.
     *
     * @param request is the customer request
     * @return list of sorted relevant rooms
     * */
    @Override
    public List<Room> findTheMostRelevant(Request request) {
        List<Room> roomList = getAvailableRooms(request);
        List<Room> newRoomList1 = new ArrayList<>();
        List<Room> newRoomList2 = new ArrayList<>();

        List<Room> finalRoomList;

        for (Room room : roomList) {
            if (request.getBeds() == room.getBeds()) {
                newRoomList1.add(room);
            } else if (request.getBeds() < room.getBeds()){
                newRoomList2.add(room);
            }
        }
        if (!newRoomList1.isEmpty()) {
            finalRoomList = newRoomList1;
        } else {
            finalRoomList = newRoomList2;
        }

        finalRoomList.sort(roomComparator);
        return finalRoomList;
    }
}

class RoomComparator implements Comparator<Room> {
    @Override
    public int compare(Room o1, Room o2) {
        int result = 0;
        int bedsSubstraction = o1.getBeds() - o2.getBeds();
        int starsSubstraction = o1.getRoomClass() - o2.getRoomClass();

        if (bedsSubstraction == 0 && starsSubstraction == 0) {
            return result;
        } else if (bedsSubstraction == 0 && starsSubstraction != 0) {
            result = starsSubstraction;
            return result;
        } else if (bedsSubstraction != 0 && starsSubstraction == 0) {
            result = bedsSubstraction;
            return result;
        } else {
            result = bedsSubstraction;
            return result;
        }
    }
}
