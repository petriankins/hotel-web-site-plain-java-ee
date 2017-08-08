package com.epam.javalab.hotelproject.repository;

import com.epam.javalab.hotelproject.model.Bill;
import com.epam.javalab.hotelproject.service.DatabaseService;
import com.epam.javalab.hotelproject.service.DatabaseServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillRepository implements BillDAO {
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
    public boolean insertBill(Bill bill) {
        try (Connection connection = databaseService.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO sql11188080.bills (number, sum, paid, id_request, created, id_room)" +
                             " VALUES (?, ?, ?, ?, ?, ?);")) {
            preparedStatement.setInt(1, bill.getNumber());
            preparedStatement.setInt(2, bill.getSum());
            preparedStatement.setInt(3, bill.getPaid());
            preparedStatement.setInt(4, bill.getIdRequest());
            preparedStatement.setDate(5, new java.sql.Date((bill.getCreated().getTime())));
            preparedStatement.setInt(6, bill.getIdRoom());
            return preparedStatement.executeUpdate() == 1 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateBill(Bill bill) {
        try (Connection connection = databaseService.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE sql11188080.bills SET 'sum' = ?, 'paid' = ?, 'id_request' = ?, 'created' = ?," +
                             " 'id_room' = ? WHERE number = ?;")) {
            preparedStatement.setInt(1, bill.getSum());
            preparedStatement.setInt(2, bill.getPaid());
            preparedStatement.setInt(3, bill.getIdRequest());
            preparedStatement.setDate(4, new java.sql.Date((bill.getCreated().getTime())));
            preparedStatement.setInt(5, bill.getIdRoom());
            preparedStatement.setInt(6, bill.getNumber());
            return preparedStatement.executeUpdate() == 1 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteBill(Bill bill) {
        try (Connection connection = databaseService.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM sql11188080.bills WHERE number = ?;")) {
            preparedStatement.setInt(1, bill.getNumber());
            return preparedStatement.executeUpdate() == 1 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
