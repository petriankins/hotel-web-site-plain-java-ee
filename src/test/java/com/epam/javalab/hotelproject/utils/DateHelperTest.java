package com.epam.javalab.hotelproject.utils;

import com.epam.javalab.hotelproject.model.Request;
import com.epam.javalab.hotelproject.repository.RequestDAO;
import com.epam.javalab.hotelproject.repository.RequestRepository;
import com.epam.javalab.hotelproject.service.DatabaseService;
import com.epam.javalab.hotelproject.service.DatabaseServiceImpl;
import org.junit.Test;

public class DateHelperTest {
    @Test
    public void calculateDaysBetweenDates() throws Exception {
        DatabaseService databaseService = DatabaseServiceImpl.getInstance();
        RequestDAO requestDAO = new RequestRepository();

        Request request = requestDAO.findByNumber(2);
        System.out.println("Date from:" + request.getDateFrom());
        System.out.println("Date to: " + request.getDateTo());
        System.out.println("Days between them " + DateHelper.calculateDaysBetweenDates(request.getDateFrom(), request.getDateTo()));
    }

}