package com.epam.javalab.hotelproject.repository;

import com.epam.javalab.hotelproject.model.User;
import com.epam.javalab.hotelproject.service.DataBaseService;
import com.epam.javalab.hotelproject.service.DataBaseServiceImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// TODO
public class UserRepository implements UserDAO {
    DataBaseService dataBaseService = DataBaseServiceImpl.getInstance();

    @Override
    public List<User> findAll() {
        List users = new ArrayList();
        try (Connection connection = dataBaseService.takeConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM sql11188080.users");) {
            while (resultSet.next()) {
                users.add(new User(resultSet.getString("first_name"), resultSet.getString("password"), resultSet.getString("email")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User findByLogin(String login) {
        return new User("", "", "");
    }

    @Override
    public boolean insertUser(User user) {
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public boolean deleteUser(User user) {
        return false;
    }
}
