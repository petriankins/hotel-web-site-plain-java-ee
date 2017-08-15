package com.epam.javalab.hotelproject.repository;

import com.epam.javalab.hotelproject.model.Bill;
import com.epam.javalab.hotelproject.model.Request;
import com.epam.javalab.hotelproject.service.DatabaseService;
import com.epam.javalab.hotelproject.service.DatabaseServiceImpl;
import com.epam.javalab.hotelproject.utils.DateHelper;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.epam.javalab.hotelproject.utils.DateHelper.*;
/**
 * Provides API for finding, inserting, deleting, updating etc. bills in/from database.
 *
 * @author Petriankin Sergey, Iaichnikov Denis
 * @version 1.0
 * @since 1.0
 */
public class BillRepository implements BillDAO {
    private final static Logger          LOGGER          = Logger.getLogger(BillRepository.class);
    private final        String          TABLE_NAME      = "bills";
    private final        DatabaseService databaseService = DatabaseServiceImpl.getInstance();

    @Override
    public List<Bill> findAll() {
        List<Bill> allBills = new ArrayList<>();
        try (Connection connection = databaseService.takeConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT * FROM " + databaseService.getDatabaseName() + "." + TABLE_NAME)) {
            while (resultSet.next()) {
                allBills.add(createBill(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return allBills;
    }

    @Override
    public boolean insertBill(Bill bill) {
        try (Connection connection = databaseService.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO " + databaseService.getDatabaseName() + "." + TABLE_NAME +
                     " (number, sum, paid, id_request, created, id_room)" +
                     " VALUES (?, ?, ?, ?, ?, ?);")) {
            preparedStatement.setInt(1, bill.getNumber());
            preparedStatement.setInt(2, bill.getSum());
            preparedStatement.setInt(3, bill.getPaid());
            preparedStatement.setInt(4, bill.getIdRequest());
            preparedStatement.setDate(5, javaToSQLDdate(bill.getDateOfCreation()));
            preparedStatement.setInt(6, bill.getIdRoom());

            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public boolean updateBill(Bill bill) {
        try (Connection connection = databaseService.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE " + databaseService.getDatabaseName() + "." + TABLE_NAME +
                     " SET 'sum' = ?, 'paid' = ?, 'id_request' = ?, 'created' = ?," +
                     " 'id_room' = ? WHERE number = ?;")) {
            preparedStatement.setInt(1, bill.getSum());
            preparedStatement.setInt(2, bill.getPaid());
            preparedStatement.setInt(3, bill.getIdRequest());
            preparedStatement.setDate(4, javaToSQLDdate(bill.getDateOfCreation()));
            preparedStatement.setInt(5, bill.getIdRoom());
            preparedStatement.setInt(6, bill.getNumber());

            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public boolean deleteBill(Bill bill) {
        try (Connection connection = databaseService.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM " + databaseService.getDatabaseName() + "." + TABLE_NAME + " WHERE number = ?;")) {
            preparedStatement.setInt(1, bill.getNumber());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int getBillId(Request request) {
        if (request == null) {
            return 0;
        }

        int requestId = request.getId();

        ResultSet resultSet = null;
        try (Connection connection = databaseService.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM " + databaseService.getDatabaseName() + "." + TABLE_NAME +
                     " WHERE id_request = ?")) {
            preparedStatement.setInt(1, requestId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.first()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                if (resultSet != null && !resultSet.isClosed()) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }

        return 0;
    }

    @Override
    public Bill findById(int id) {
        if (id == 0) return emptyBill();

        ResultSet rs = null;
        try (Connection con = databaseService.takeConnection();
             PreparedStatement ps = con.prepareStatement(
                     "SELECT * FROM " + databaseService.getDatabaseName() + "." + TABLE_NAME + " WHERE id = ?")) {
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                LOGGER.debug("Found bill for id: " + id);
                return createBill(rs);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }

        return emptyBill();
    }

    private Bill emptyBill() {
        return new Bill();
    }

    private Bill createBill(ResultSet resultSet) throws SQLException {
        return new Bill(resultSet.getInt("id"),
                        resultSet.getInt("number"),
                        resultSet.getInt("sum"),
                        resultSet.getInt("paid"),
                        resultSet.getInt("id_request"),
                        resultSet.getDate("created"),
                        resultSet.getInt("id_room"));
    }
}
