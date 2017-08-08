package com.epam.javalab.hotelproject.repository;

import com.epam.javalab.hotelproject.model.Bill;
import com.epam.javalab.hotelproject.service.DatabaseService;
import com.epam.javalab.hotelproject.service.DatabaseServiceImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BillRepository implements BillDAO{
    DatabaseService databaseService = DatabaseServiceImpl.getInstance();

    @Override
    public List<Bill> findAll() {
        List<Bill> allBills = new ArrayList<>();
        try (Connection connection = databaseService.takeConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM sql11188080.bills")) {
            while (resultSet.next()) {
                allBills.add(new Bill(resultSet.getInt("id"),
                                        resultSet.getInt("number"),
                                        resultSet.getInt("sum"),
                                        resultSet.getInt("paid"),
                                        resultSet.getInt("id_request"),
                                        resultSet.getDate("created"),
                                        resultSet.getInt("id_room")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allBills;
    }

    @Override
    public Bill findByNumber(int number) {

        return null;
    }

    @Override
    public boolean insertBill(Bill bill) {
        return false;
    }

    @Override
    public boolean updateBill(Bill bill) {
        return false;
    }

    @Override
    public boolean deleteBill(Bill bill) {
        return false;
    }
}
