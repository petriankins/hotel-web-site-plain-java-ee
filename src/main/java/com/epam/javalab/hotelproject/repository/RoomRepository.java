package com.epam.javalab.hotelproject.repository;

import com.epam.javalab.hotelproject.model.Room;
import com.epam.javalab.hotelproject.service.DatabaseService;
import com.epam.javalab.hotelproject.service.DatabaseServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomRepository implements RoomDAO {
    DatabaseService databaseService = DatabaseServiceImpl.getInstance();
    private Room emptyRoom = new Room();
    @Override
    public List<Room> finAll() {
        List<Room> roomList = new ArrayList<>();
        try (Connection connection = databaseService.takeConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM sql11188080.rooms ")) {
            while (resultSet.next()) {
                 roomList.add(new Room(resultSet.getInt("id"),
                                        resultSet.getInt("number"),
                                        resultSet.getInt("beds"),
                                        resultSet.getInt("id_class")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }

    @Override
    public Room findByNumber(int number) {
      return null;
    }

    @Override
    public Room findByBeds(int beds) {
        return null;
    }

    @Override
    public Room findByClass(int roomClass) {
        return null;
    }

    @Override
    public boolean insertRoom() {
        return false;
    }

    @Override
    public boolean updateRoom() {
        return false;
    }

    @Override
    public boolean deleteRoom() {
        return false;
    }
}
