package com.epam.javalab.hotelproject.repository;

import com.epam.javalab.hotelproject.model.User;

import java.util.Collections;
import java.util.List;

// TODO
public class UserRepository implements UserDAO {
    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findByLogin(String login) {
        return new User("", "", "", "");
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
