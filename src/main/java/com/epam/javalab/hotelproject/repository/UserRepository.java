package com.epam.javalab.hotelproject.repository;

import com.epam.javalab.hotelproject.model.User;
import com.epam.javalab.hotelproject.service.DataBaseService;
import com.epam.javalab.hotelproject.service.DataBaseServiceImpl;
import com.epam.javalab.hotelproject.utils.Validator;

import java.sql.*;
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
                users.add(new User(resultSet.getString("first_name"), resultSet.getString("last_name"),
                                   resultSet.getString("password"), resultSet.getString("email")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    private User emptyUser() {
        return new User();
    }

    @Override
    public User findByLogin(String login) {
        if (login.isEmpty()) {
            return emptyUser();
        }
        ResultSet resultSet = null;
        try (Connection connection = dataBaseService.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM sql11188080.users WHERE email = ?");
        ) {
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                User user = new User(resultSet.getString("first_name"), resultSet.getString("last_name"),
                                     resultSet.getString("email"), resultSet.getString("password"));

                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null && !resultSet.isClosed()) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return emptyUser();
    }

    @Override
    public boolean insertUser(User user) {
        if (Validator.validateUserBean(user)) {
            try (Connection connection = dataBaseService.takeConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(
                         "INSERT INTO `sql11188080`.`users` (`email`, `password`, `first_name`, `last_name`) VALUES (?, ?, ?, ?)")) {
                preparedStatement.setString(1, user.getLogin());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getName());
                preparedStatement.setString(4, user.getLastName());
                return preparedStatement.executeUpdate() == 1 ? true : false;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        if (Validator.validateUserBean(user)) {
            try (Connection connection = dataBaseService.takeConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE `sql11188080`.`users` SET (`password`, `first_name`, `last_name`) VALUES (?, ?, ?) WHERE `email` = `?`")) {
                preparedStatement.setString(1,user.getPassword());
                preparedStatement.setString(2, user.getName());
                preparedStatement.setString(3, user.getLastName());
                preparedStatement.setString(4, user.getLogin());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean deleteUser(User user) {
        if (Validator.validateUserBean(user)) {
            try (Connection connection = dataBaseService.takeConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE `sql11188080`.`users` WHERE `email` = `?`"
            )) {
                preparedStatement.setString(1, user.getLogin());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
