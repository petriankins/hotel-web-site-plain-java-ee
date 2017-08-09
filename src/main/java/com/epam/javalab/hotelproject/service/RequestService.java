package com.epam.javalab.hotelproject.service;

import com.epam.javalab.hotelproject.model.Request;
import com.epam.javalab.hotelproject.model.User;

import java.util.List;

public interface RequestService {
    List<Request> findAll();

    boolean saveRequest(Request request);

    List<Request> findByUser(User user);

    Request findByNumber(int number);

    boolean deleteRequest(Request request);

    boolean updateRequest(Request request);

    List<Request> findAllUnhandledRequests();
}
