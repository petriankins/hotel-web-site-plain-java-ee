package com.epam.javalab.hotelproject.repository;

import com.epam.javalab.hotelproject.model.Room;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class RoomRepositoryTest {
    RoomDAO roomDAO = new RoomRepository();
    Map<Integer, Room> roomMap;

    @Before
    public void setUp() {
        roomMap = new HashMap<>();
        addRoomsToMap(roomMap, new Room(111, 4, 2));
        addRoomsToMap(roomMap, new Room(1, 2, 3));
        roomMap.forEach((k, v) -> roomDAO.insertRoom(v));
    }

    private void addRoomsToMap(Map<Integer, Room> roomMap, Room room) {
        roomMap.put(room.getNumber(), room);
    }

    @Test
    public void finAll() throws Exception {
    }

    @Test
    public void findByNumber() throws Exception {
    }

    @Test
    public void findByBeds() throws Exception {
    }

    @Test
    public void findByClass() throws Exception {
    }

    @Test
    public void insertRoom() throws Exception {
    }

    @Test
    public void updateRoom() throws Exception {
    }

    @Test
    public void deleteRoom() throws Exception {
    }

}