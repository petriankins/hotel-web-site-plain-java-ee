package com.epam.javalab.hotelproject.repository;

import com.epam.javalab.hotelproject.model.Room;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

public class RoomRepositoryTest {
    private final RoomDAO roomDAO = new RoomRepository();
    private Map<Integer, Room> roomMap;

    private void addRoomsToMap(Map<Integer, Room> roomMap, Room room) {
        roomMap.put(room.getNumber(), room);
    }

    private boolean compareRooms(Room room1, Room room2) {
        if (room1.getNumber() != room2.getNumber()) {
            printDifferences("Number " , Integer.valueOf(room1.getNumber()).toString(), Integer.valueOf(room2.getNumber()).toString());
            return false;
        }
        if (room1.getRoomClass() != room2.getRoomClass()) {
            printDifferences("Room class " , Integer.valueOf(room1.getRoomClass()).toString(), Integer.valueOf(room2.getRoomClass()).toString());
            return false;
        }
        if (room1.getBeds() != room2.getBeds()) {
            printDifferences("Beds " , Integer.valueOf(room1.getBeds()).toString(), Integer.valueOf(room2.getBeds()).toString());
            return false;
        }
        return true;

    }

    private void printDifferences(String field, String expected, String actual) {
        System.out.println(field + " doesn't match");
        System.out.println("Expected: " + expected);
        System.out.println("Actual: " + actual);
    }

    @Before
    public void setUp() {
        roomMap = new HashMap<>();
        addRoomsToMap(roomMap, new Room(111, 4, 2));
        addRoomsToMap(roomMap, new Room(1, 2, 3));
        roomMap.forEach((k, v) -> roomDAO.insertRoom(v));
    }

    @After
    public void tearDown() {
        roomMap.forEach((k,v) -> roomDAO.deleteRoom(v));
    }

    @Test
    public void findAll() throws Exception {
        List<Room> allRooms = roomDAO.findAll();
        assertThat(allRooms.size(), is(roomMap.size()));
        for (Room room : allRooms) {
            assertThat(roomMap.get(room.getNumber()), notNullValue());
            assertThat(compareRooms(roomMap.get(room.getNumber()), room), is(true));
        }
    }

    @Test
    public void findByNumber() throws Exception {
        roomMap.forEach((k, v) -> {
            Room room = roomDAO.findByNumber(v.getNumber());
            assertThat(compareRooms(v, room), is(true));
        });
    }

    @Test
    public void findByBeds() throws Exception {
        roomMap.forEach((k, v) -> {
            Room room = roomDAO.findByBeds(v.getBeds());
            assertThat(compareRooms(v, room), is(true));
        });
    }

    @Test
    public void findByClass() throws Exception {
        roomMap.forEach((k, v) -> {
            Room room = roomDAO.findByClass(v.getRoomClass());
            assertThat(compareRooms(v, room), is(true));
        });
    }

    private Room makeFakeRoom() {
        return new Room(100, 4, 5);
    }
    @Test
    public void insertRoom() throws Exception {
        Room room = makeFakeRoom();
        assertThat(roomDAO.insertRoom(room), is(true));
        Room insertedRoom = roomDAO.findByNumber(room.getNumber());
        assertThat(compareRooms(insertedRoom, room), is(true));
        assertThat(insertedRoom.getId(), not(0));
        roomDAO.deleteRoom(room);
    }

    @Test
    public void updateRoom() throws Exception {
        roomMap.forEach((k, v) -> {
            v.setBeds(666);
            assertThat(roomDAO.updateRoom(v), is(true));
        });
        roomMap.forEach((k,v) -> assertThat(compareRooms(roomDAO.findByNumber(v.getNumber()), v), is(true)) );

    }

    @Test
    public void deleteRoom() throws Exception {
        Room room = makeFakeRoom();
        roomDAO.insertRoom(room);
        assertThat(compareRooms(roomDAO.findByNumber(room.getNumber()), room), is(true));
        assertThat(roomDAO.deleteRoom(room), is(true));
        assertThat(compareRooms(roomDAO.findByNumber(room.getNumber()), new Room()), is(true));
    }

}