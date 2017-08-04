package com.epam.javalab.hotelproject.repository;

import com.epam.javalab.hotelproject.model.Room;
import com.epam.javalab.hotelproject.service.DatabaseService;
import com.epam.javalab.hotelproject.service.DatabaseServiceImpl;
import com.epam.javalab.hotelproject.utils.Validator;

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
        if (number == 0) {
            return emptyRoom;
        }
        ResultSet resultSet = null;
        try (Connection connection = databaseService.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM sql11188080.rooms WHERE number = ?")) {
            preparedStatement.setInt(1, number);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Room(resultSet.getInt("id"),
                        resultSet.getInt("number"),
                        resultSet.getInt("beds"),
                        resultSet.getInt("id_class"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null && !resultSet.isClosed()) {

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return emptyRoom;
    }

    @Override
    public Room findByBeds(int beds) {
        if (beds == 0) {
            return emptyRoom;
        }
        ResultSet resultSet = null;
        try (Connection connection = databaseService.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM sql11188080.rooms WHERE beds = ?")) {
            preparedStatement.setInt(1, beds);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Room(resultSet.getInt("id"),
                        resultSet.getInt("number"),
                        resultSet.getInt("beds"),
                        resultSet.getInt("id_class"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null && !resultSet.isClosed()) {

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return emptyRoom;
    }

    @Override
    public Room findByClass(int roomClass) {
        if (roomClass == 0) {
            return emptyRoom;
        }
        ResultSet resultSet = null;
        try (Connection connection = databaseService.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM sql11188080.rooms WHERE id_class = ?")) {
            preparedStatement.setInt(1, roomClass);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Room(resultSet.getInt("id"),
                        resultSet.getInt("number"),
                        resultSet.getInt("beds"),
                        resultSet.getInt("id_class"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null && !resultSet.isClosed()) {

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return emptyRoom;
    }

    @Override
    public boolean insertRoom() {
//        if (Validator.validateRoomBean())
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

