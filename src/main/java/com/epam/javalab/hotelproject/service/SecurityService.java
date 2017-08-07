package com.epam.javalab.hotelproject.service;

import com.epam.javalab.hotelproject.model.User;

public interface SecurityService {
    String hash(String password);

    boolean authenticate(User user);
}