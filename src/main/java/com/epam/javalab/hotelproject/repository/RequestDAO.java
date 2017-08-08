package com.epam.javalab.hotelproject.repository;

import com.epam.javalab.hotelproject.model.Request;
import com.epam.javalab.hotelproject.model.User;

import java.util.List;

public interface RequestDAO {
    List<Request> findAll();

    Request findByNumber(int number);

    List<Request> findByUser(User user);

    List<Request> findAllUnhandledRequests();

    boolean insertRequest(Request request);

    boolean updateRequest(Request request);

    boolean deleteRequest(Request request);

}
