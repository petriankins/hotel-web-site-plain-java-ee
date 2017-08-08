package com.epam.javalab.hotelproject.service;

import com.epam.javalab.hotelproject.model.User;

public interface UserService {
    boolean registerUser(User user);

    boolean authenticate(User user);

    User findByLogin(User user);
}