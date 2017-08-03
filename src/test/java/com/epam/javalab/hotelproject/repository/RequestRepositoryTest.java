package com.epam.javalab.hotelproject.repository;

import com.epam.javalab.hotelproject.model.Request;
import com.sun.org.apache.regexp.internal.RE;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class RequestRepositoryTest {
    RequestDAO requestDAO = new RequestRepository();

    Map<Integer, Request> requestsMap;

    @Before
    public void setUp() throws Exception {
        requestsMap = new HashMap<>();

        addRequestToMap(requestsMap, new Request(1, 753, 1, 1, new Date(System.currentTimeMillis()),
                                                 new Date(System.currentTimeMillis()),
                                                 "Must have at lead 10 windows!"));
        addRequestToMap(requestsMap, new Request(1, 753, 2, 1, new Date(System.currentTimeMillis()),
                                                 new Date(System.currentTimeMillis()),
                                                 "i want huge minibar"));

        requestsMap.forEach((k, v) -> requestDAO.insertRequest(v));
    }

    @After
    public void tearDown() throws Exception {
        requestsMap.forEach((k, v) -> requestDAO.deleteRequest(v));
    }

    private void addRequestToMap(Map<Integer, Request> requestMap, Request request) {
        requestMap.put(request.getNumber(), request);
    }

    private boolean compareRequests(Request request1, Request request2) {
        if (!(request1.getNumber() != request2.getNumber())) return false;
        if (!(request1.getBeds() != request2.getBeds())) return false;
        if (!(request1.getClassID() != request2.getClassID())) return false;
        if (!(request1.getDateFrom() != request2.getDateFrom())) return false;
        if (!(request1.getDateTo() != request2.getDateTo())) return false;
        if (!request1.getComments().equals(request2.getComments())) return false;

        return true;
    }

    @Test
    public void findAll() throws Exception {
        List<Request> allRequests = requestDAO.findAll();
        assertThat(allRequests.size(), is(requestsMap.size()));
        for (Request request : allRequests) {
            assertThat(requestsMap.get(request.getNumber()), not(null));
            assertThat(compareRequests(requestsMap.get(request.getNumber()), request), is(true));
        }
    }

    @Test
    public void findByNumber() throws Exception {
        requestsMap.forEach((k, v) -> {
            Request request = requestDAO.findByNumber(v.getNumber());
            assertThat(compareRequests(v, request), is(true));
        });
    }

    @Test
    public void insertRequest() throws Exception {
        Request newRequest = makeFakeRequest();
        assertThat(requestDAO.insertRequest(newRequest), is(true));
        Request storedRequest = requestDAO.findByNumber(newRequest.getNumber());
        assertThat(compareRequests(newRequest, storedRequest), is(true));
        assertThat(storedRequest.getId(), not(0));
    }

    private Request makeFakeRequest() {
        return new Request(55, 753, 100, 1, new Date(System.currentTimeMillis()),
                           new Date(System.currentTimeMillis()), "Yes, we need 100 beds");
    }

    @Test
    public void updateRequest() throws Exception {
        requestsMap.forEach((k, v) -> {
            v.setComments("I am fake request!");
            assertThat(requestDAO.updateRequest(v), is(true));
        });
        requestsMap.forEach((k, v) -> assertThat(compareRequests(requestDAO.findByNumber(v.getNumber()), v), is(true)));
    }

    @Test
    public void deleteRequest() throws Exception {
        Request newRequest = makeFakeRequest();
        requestDAO.insertRequest(newRequest);
        assertThat(compareRequests(requestDAO.findByNumber(newRequest.getNumber()), newRequest), is(true));
        assertThat(requestDAO.deleteRequest(newRequest), is(true));
        assertThat(compareRequests(requestDAO.findByNumber(newRequest.getNumber()), newRequest), is(false));
    }
}