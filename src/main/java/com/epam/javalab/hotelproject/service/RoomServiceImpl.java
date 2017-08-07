package com.epam.javalab.hotelproject.service;

import com.epam.javalab.hotelproject.model.Room;
import com.epam.javalab.hotelproject.repository.RoomDAO;
import com.epam.javalab.hotelproject.repository.RoomRepository;

import java.util.List;

public class RoomServiceImpl implements RoomService {
    RoomDAO roomDAO = new RoomRepository();

    @Override
    public List<Room> findAll() {
        return roomDAO.finAll();
    }

    @Override
    public Room findByNumber(int number) {
        return roomDAO.findByNumber();
    }

    @Override
    public Room findTheMostRelevant() {
        return null;
    }

}
