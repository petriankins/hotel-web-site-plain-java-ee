package com.epam.javalab.hotelproject.service;

import com.epam.javalab.hotelproject.model.Bill;
import com.epam.javalab.hotelproject.model.Request;
import com.epam.javalab.hotelproject.model.Room;

import java.util.List;

public interface BillService {
    List<Bill> findAll();
    Bill findByNumber();
    Bill createBill(Request request, Room room);
    boolean saveBill();
    boolean deleteBill();
}
