package com.epam.javalab.hotelproject.service;

import com.epam.javalab.hotelproject.model.Request;
import com.epam.javalab.hotelproject.model.Room;
import com.epam.javalab.hotelproject.model.RoomStatus;

public interface RoomStatusService {
    boolean save(RoomStatus roomStatus);

    boolean edit(RoomStatus roomStatus);

    boolean delete(RoomStatus roomStatus);

    boolean bookRoom(Request request, Room room);
}
