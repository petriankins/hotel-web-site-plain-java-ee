package com.epam.javalab.hotelproject.service;

import com.epam.javalab.hotelproject.model.Request;
import com.epam.javalab.hotelproject.model.User;

import java.util.List;

public interface RequestService {


    List<Request> findByUser(User user);

    List<Request> findByNumber(int number);
}
