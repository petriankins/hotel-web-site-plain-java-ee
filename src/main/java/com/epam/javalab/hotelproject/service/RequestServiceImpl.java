package com.epam.javalab.hotelproject.service;

import com.epam.javalab.hotelproject.model.Request;
import com.epam.javalab.hotelproject.model.User;
import com.epam.javalab.hotelproject.repository.RequestDAO;
import com.epam.javalab.hotelproject.repository.RequestRepository;

import static com.epam.javalab.hotelproject.utils.Validator.validateRequestBean;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RequestServiceImpl implements RequestService {
    RequestDAO requestDAO = new RequestRepository();

    @Override
    public List<Request> findAll() {
        return requestDAO.findAll();
    }

    @Override
    public boolean saveRequest(Request request) {
        request.setNumber(generateRequestNumber());
        if (!validateRequestBean(request)) {
            return false;
        }
        return createRequest(request);
    }

    @Override
    public boolean deleteRequest(Request request) {
        return requestDAO.deleteRequest(request);
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

    @Override
    public List<Request> findAllUnhandledRequests() {
        List<Request> handledRequests = requestDAO.findAllHandledRequests();
        List<Request> allRequests = requestDAO.findAll();
        allRequests.removeAll(handledRequests);
        return allRequests;
    }

    private static int generateRequestNumber() {
        AtomicInteger atomicInteger;
        atomicInteger = new AtomicInteger(RequestRepository.returnMaxRequestNumber());
        return incrementRequestNumber(atomicInteger);
    }

    private static int incrementRequestNumber(AtomicInteger number) {
        return number.incrementAndGet();
    }
}

