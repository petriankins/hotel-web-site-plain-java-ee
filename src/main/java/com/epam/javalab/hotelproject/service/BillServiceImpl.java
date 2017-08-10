package com.epam.javalab.hotelproject.service;

import com.epam.javalab.hotelproject.model.Bill;
import com.epam.javalab.hotelproject.model.Request;
import com.epam.javalab.hotelproject.model.Room;
import com.epam.javalab.hotelproject.repository.BillDAO;
import com.epam.javalab.hotelproject.repository.BillRepository;
import com.epam.javalab.hotelproject.utils.DateHelper;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.List;

public class BillServiceImpl implements BillService {
    private final static Logger  LOGGER  = Logger.getLogger(BillServiceImpl.class);
    private final BillDAO billDAO = new BillRepository();

    @Override
    public List<Bill> findAll() {
        return billDAO.findAll();
    }

    @Override
    public Bill createBill(Request request, Room room) {
        Bill createdBill = new Bill(request.getNumber(), calculatePrice(request), 0, request.getId(),
                                    new Date(System.currentTimeMillis()), room.getId());
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

    @Override
    public int getBillId(Request request) {
        return billDAO.getBillId(request);
    }

    @Override
    public Bill getRequestBill(Request request) {
        LOGGER.debug("Looking up bill for Request#" + request.getNumber());
        int bilId = billDAO.getBillId(request);
        LOGGER.debug("Received billId " + bilId);
        if (bilId == 0) {
            LOGGER.debug("Sending empty bill");
            return new Bill();
        }

        return billDAO.findById(bilId);
    }

    private int calculatePrice(Request request) {
        int daysRatio = DateHelper.calculateDaysBetweenDates(request.getDateFrom(), request.getDateTo());
        if (daysRatio < 1) {
            daysRatio = 1;
        }
        int bedsRatio = request.getBeds();
        int classRatio = request.getClassID();
        int totalSum = ((daysRatio * 10) + bedsRatio) * classRatio;
        return totalSum;
    }
}
