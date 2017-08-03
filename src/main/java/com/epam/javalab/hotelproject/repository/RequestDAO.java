package com.epam.javalab.hotelproject.repository;

import com.epam.javalab.hotelproject.model.Request;

import java.util.List;

public interface RequestDAO {
    List<Request> findAll();

    Request findByNumber(int number);

    boolean insertRequest(Request request);

    boolean updateRequest(Request request);

    boolean deleteRequest(Request request);

}
