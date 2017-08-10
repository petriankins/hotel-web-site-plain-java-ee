package com.epam.javalab.hotelproject.repository;

import com.epam.javalab.hotelproject.model.Bill;
import com.epam.javalab.hotelproject.model.Request;

import java.util.List;

public interface BillDAO {
    List<Bill> findAll();

    boolean insertBill(Bill bill);

    boolean updateBill(Bill bill);

    boolean deleteBill(Bill bill);

    int getBillId(Request request);

    Bill findById(int id);
}
