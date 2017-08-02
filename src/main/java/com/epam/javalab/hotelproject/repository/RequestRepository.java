package com.epam.javalab.hotelproject.repository;

import com.epam.javalab.hotelproject.model.Request;
import com.epam.javalab.hotelproject.service.DatabaseService;
import com.epam.javalab.hotelproject.service.DatabaseServiceImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RequestRepository implements RequestDAO {
    DatabaseService databaseService = DatabaseServiceImpl.getInstance();

    @Override
    public List<Request> findAll() {
        List<Request> requests = new ArrayList<>();
        try (Connection connection = databaseService.takeConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM sql11188080.requests");) {
            while (resultSet.next()) {
                requests.add(new Request(resultSet.getInt("id"), resultSet.getInt("number"), resultSet.getInt("user_id"),resultSet.getInt("beds"), resultSet.getInt("id_class"), resultSet.getDate("date_from"), resultSet.getDate("date_from"), resultSet.getString("comments")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return requests;
    }

    @Override
    public Request findByNumber(int number) {
        return null;
    }

    @Override
    public boolean insertRequest(Request request) {
        return false;
    }

    @Override
    public boolean updateRequest(Request request) {
        return false;
    }

    @Override
    public boolean deleteRequest(Request request) {
        return false;
    }
}
