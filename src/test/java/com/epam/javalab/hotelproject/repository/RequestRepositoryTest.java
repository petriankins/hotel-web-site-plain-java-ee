package com.epam.javalab.hotelproject.repository;

import com.epam.javalab.hotelproject.model.Request;
import com.epam.javalab.hotelproject.model.User;
import com.sun.org.apache.regexp.internal.RE;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class RequestRepositoryTest {
    private final RequestDAO requestDAO = new RequestRepository();
    private final UserDAO    userDAO    = new UserRepository();
    private final User       admin      = userDAO.findByLogin("info@hotel.project");

    @Test
    public void findAll() throws Exception {
        assertThat(requestDAO.findAll().size(), is(0));
        addFakeRequests();
        assertThat(requestDAO.findAll().size(), is(1));
        assertThat(requestDAO.findAll().size(), is(1));
    }

    @Test
    public void findByNumber() throws Exception {
        assertThat(requestDAO.findByNumber(1).getNumber(), is(0));
        addFakeRequests();
        assertThat(requestDAO.findByNumber(1).getNumber(), is(1));
    }

    @Test
    public void insertRequest() throws Exception {
        assertThat(requestDAO.findByNumber(1).getNumber(), is(0));
        Request fakeRequest = createFakeRequest(admin);
        assertThat(requestDAO.insertRequest(fakeRequest), is(true));
        assertThat(requestDAO.findByNumber(1).getNumber(), is(1));
    }

    @Test
    public void updateRequest() throws Exception {
        addFakeRequests();
        assertThat(requestDAO.findByNumber(1).getBeds(), is(1));
        Request fakeRequest = createFakeRequest(admin);
        fakeRequest.setBeds(10);
        assertThat(requestDAO.updateRequest(fakeRequest), is(true));
        assertThat(requestDAO.findByNumber(1).getBeds(), is(10));
    }

    @Test
    public void deleteRequest() throws Exception {
        addFakeRequests();
        Request fakeRequest = createFakeRequest(admin);
        assertThat(requestDAO.findByNumber(1).getNumber(), is(1));
        assertThat(requestDAO.deleteRequest(fakeRequest), is(true));
        assertThat(requestDAO.findByNumber(1).getNumber(), is(0));
    }

    private void addFakeRequests() {
        requestDAO.insertRequest(createFakeRequest(admin));
    }

    private Request createFakeRequest(User admin) {
        return new Request(1, admin.getId(), 1, 1, new Date(System.currentTimeMillis()),
                           new Date(System.currentTimeMillis()), "");
    }
}