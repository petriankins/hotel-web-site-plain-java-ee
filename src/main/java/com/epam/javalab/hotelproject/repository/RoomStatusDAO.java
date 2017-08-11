package com.epam.javalab.hotelproject.repository;

import com.epam.javalab.hotelproject.model.RoomStatus;

import java.util.List;

public interface RoomStatusDAO {
    List<RoomStatus> findAll();

    RoomStatus findById(int id);

    RoomStatus findByRoomId(int roomId);

    boolean insert(RoomStatus roomStatus);

    boolean update(RoomStatus roomStatus);

    boolean delete(RoomStatus roomStatus);
}
