package com.epam.javalab.hotelproject.service;

import com.epam.javalab.hotelproject.model.Bill;
import com.epam.javalab.hotelproject.model.Request;
import com.epam.javalab.hotelproject.model.Room;
import com.epam.javalab.hotelproject.repository.BillDAO;
import com.epam.javalab.hotelproject.repository.BillRepository;
import com.epam.javalab.hotelproject.utils.DateHelper;

import java.util.Date;
import java.util.List;

public class BillServiceImpl implements BillService {
    BillDAO billDAO = new BillRepository();

    @Override
    public List<Bill> findAll() {
        return billDAO.findAll();
    }

    @Override
    public Bill createBill(Request request, Room room) {
        Bill createdBill = new Bill(request.getNumber(), calculatePrice(request), 0, request.getId(), new Date(System.currentTimeMillis()), room.getId());
        saveBill(createdBill);
        return createdBill;
    }

    private boolean saveBill(Bill bill) {
        return billDAO.insertBill(bill);
    }

    @Override
    public boolean deleteBill(Bill bill) {
        return billDAO.deleteBill(bill);
    }

    private int calculatePrice(Request request) {
        int daysRatio = DateHelper.calculateDaysBetweenDates(request.getDateFrom(), request.getDateTo());
        int bedsRatio = request.getBeds();
        int classRatio = request.getClassID();
        int totalSum = ((daysRatio * 10) + bedsRatio) * classRatio;
        return totalSum;
    }
}
