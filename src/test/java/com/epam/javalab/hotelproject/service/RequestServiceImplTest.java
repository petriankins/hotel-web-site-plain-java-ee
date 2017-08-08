package com.epam.javalab.hotelproject.service;

import com.epam.javalab.hotelproject.model.Request;
import org.junit.Test;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class RequestServiceImplTest {
    private final RequestService requestService = new RequestServiceImpl();

    @Test
    public void saveRequest() throws Exception {
        Request firstRequest = new Request(753, 1,1, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), "");
        assertThat(requestService.saveRequest(firstRequest), is(true));
        Request secondRequest = new Request(753, 2, 2, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), "");
        assertThat(requestService.saveRequest(secondRequest), is(true));
        assertThat(secondRequest.getNumber() - firstRequest.getNumber(), is(1));
        requestService.deleteRequest(firstRequest);
        requestService.deleteRequest(secondRequest);
    }
}