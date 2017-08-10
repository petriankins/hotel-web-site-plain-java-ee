package com.epam.javalab.hotelproject.service;

import com.epam.javalab.hotelproject.model.Bill;
import com.epam.javalab.hotelproject.model.Request;
import com.epam.javalab.hotelproject.model.Room;

import java.util.List;

public interface BillService {
    List<Bill> findAll();
    Bill createBill(Request request, Room room);
    boolean deleteBill(Bill bill);

    int getBillId(Request request);

    Bill getRequestBill(Request request);
}
