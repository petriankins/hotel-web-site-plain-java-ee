package com.epam.javalab.hotelproject.repository;

import com.epam.javalab.hotelproject.model.Request;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

public class RequestRepositoryTest {
    private final RequestDAO requestDAO = new RequestRepository();

    private Map<Integer, Request> requestsMap;

    @Before
    public void setUp() throws Exception {
        requestsMap = new HashMap<>();

        addRequestToMap(requestsMap, new Request(1, 753, 1, 1, new Date(System.currentTimeMillis()),
                                                 new Date(System.currentTimeMillis()),
                                                 "Must have at lead 10 windows!"));
        addRequestToMap(requestsMap, new Request(1, 753, 2, 1, new Date(System.currentTimeMillis()),
                                                 new Date(System.currentTimeMillis()),
                                                 "i want huge minibar"));
        addRequestToMap(requestsMap, new Request(99, 753, 2, 1, new Date(System.currentTimeMillis()),
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
        if ((request1.getNumber() != request2.getNumber())) {
            printDifferences("Number", Integer.valueOf(request1.getNumber()).toString(),
                             Integer.valueOf(request2.getNumber()).toString());
            return false;
        }
        if ((request1.getBeds() != request2.getBeds())) {
            printDifferences("Amount of beds", Integer.valueOf(request1.getBeds()).toString(),
                             Integer.valueOf(request2.getBeds()).toString());
            return false;
        }
        if ((request1.getClassID() != request2.getClassID())) {
            printDifferences("Class id", Integer.valueOf(request1.getClassID()).toString(),
                             Integer.valueOf(request2.getClassID()).toString());
            return false;
        }
        if (compareDates(request1.getDateFrom(), request2.getDateFrom())) {
            printDifferences("Date from", request1.getDateFrom().toString(), request2.getDateFrom().toString());
            return false;
        }
        if (compareDates(request1.getDateTo(), request2.getDateTo())) {
            printDifferences("Date to", request1.getDateTo().toString(), request2.getDateTo().toString());
            return false;
        }
        if (!request1.getComments().equals(request2.getComments())) {
            printDifferences("Comments", request1.getComments(), request2.getComments());
            return false;
        }

        return true;
    }

    private void printDifferences(String field, String expected, String actual) {
        System.out.println(field + " does not match");
        System.out.println("Expected: " + expected);
        System.out.println("Actual: " + actual);
    }

    private boolean compareDates(Date date1, Date date2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return !simpleDateFormat.format(date1).equals(simpleDateFormat.format(date2));
    }

    @Test
    public void findAll() throws Exception {
        List<Request> allRequests = requestDAO.findAll();
        int foundRequests = 0;
        for (Request request : allRequests) {
            assertThat(requestsMap.getOrDefault(request.getNumber(), request), notNullValue());
            assertThat(compareRequests(requestsMap.getOrDefault(request.getNumber(), request), request), is(true));
            if (requestsMap.get(request.getNumber()) != null) foundRequests++;
        }
        assertThat(foundRequests, is(requestsMap.size()));
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
        requestDAO.deleteRequest(newRequest);
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
        assertThat(compareRequests(requestDAO.findByNumber(newRequest.getNumber()), new Request()), is(true));
    }

    @Test
    public void returnMaxRequestNumber() throws Exception {
        SortedMap<Integer, Request> sortedMap = new TreeMap<>(requestsMap);
        assertThat(RequestRepository.returnMaxRequestNumber(), is(sortedMap.lastKey()));
    }

    @Test
    public void findUnhandledRequests() throws Exception {

    }
}