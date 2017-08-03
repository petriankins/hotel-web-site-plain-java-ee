package com.epam.javalab.hotelproject.repository;

import com.epam.javalab.hotelproject.model.User;

import java.util.List;

public interface UserDAO {
    List<User> findAll();
    User findByLogin(String login);
    boolean insertUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(User user);
}
