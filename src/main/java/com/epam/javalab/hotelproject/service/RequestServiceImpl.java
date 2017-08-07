package com.epam.javalab.hotelproject.service;

import com.epam.javalab.hotelproject.model.Request;
import com.epam.javalab.hotelproject.model.User;
import com.epam.javalab.hotelproject.repository.RequestDAO;
import com.epam.javalab.hotelproject.repository.RequestRepository;

import static com.epam.javalab.hotelproject.utils.Validator.validateRequestBean;

import java.util.Comparator;
import java.util.List;

public class RequestServiceImpl implements RequestService {
    RequestDAO requestDAO = new RequestRepository();

    @Override
    public List<Request> findAll() {
        return requestDAO.findAll();
    }

    @Override
    public boolean saveRequest(Request request) {
        if (!validateRequestBean(request)) {
            return false;
        }
        return createRequest(request);
    }

    @Override
    public Request findByNumber(int number) {
        return requestDAO.findByNumber(number);
    }

    @Override
    public List<Request> findByUser(User user) {
        return requestDAO.findByUser(user);
    }

    private boolean createRequest(Request request) {
        return requestDAO.insertRequest(request);
    }
}

