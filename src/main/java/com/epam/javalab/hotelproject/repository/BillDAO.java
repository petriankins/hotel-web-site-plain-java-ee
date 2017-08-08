package com.epam.javalab.hotelproject.repository;

import com.epam.javalab.hotelproject.model.Bill;

import java.util.List;

public interface BillDAO {
    List<Bill> findAll();

    Bill findByNumber(int number);

    boolean insertBill(Bill bill);

    boolean updateBill(Bill bill);

    boolean deleteBill(Bill bill);


}
