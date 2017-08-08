package com.epam.javalab.hotelproject.service;

import com.epam.javalab.hotelproject.model.Bill;
import com.epam.javalab.hotelproject.model.Request;
import com.epam.javalab.hotelproject.utils.DateHelper;

import java.util.Date;
import java.util.List;

public class BillServiceImpl implements BillService {
    @Override
    public List<Bill> findAll() {
        return null;
    }

    @Override
    public Bill findByNumber() {
        return null;
    }

    @Override
    public Bill createBill(Request request) {
        int daysRatio = DateHelper.calculateDaysBetweenDates(request.getDateFrom(), request.getDateTo());
        int bedsRatio = request.getBeds();
        int classRatio = request.getClassID();
        int totalSum = ((daysRatio * 10) + bedsRatio) * classRatio;
        return new Bill(1, totalSum, 0, request.getId(), new Date(System.currentTimeMillis()), 1);
    }

    @Override
    public boolean saveBill() {
        return false;
    }

    @Override
    public boolean deleteBill() {
        return false;
    }
}
