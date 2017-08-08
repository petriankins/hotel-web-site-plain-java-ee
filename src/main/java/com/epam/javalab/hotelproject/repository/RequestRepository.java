package com.epam.javalab.hotelproject.repository;

import com.epam.javalab.hotelproject.model.Request;
import com.epam.javalab.hotelproject.model.User;
import com.epam.javalab.hotelproject.service.DatabaseService;
import com.epam.javalab.hotelproject.service.DatabaseServiceImpl;
import com.epam.javalab.hotelproject.utils.Validator;

import static com.epam.javalab.hotelproject.utils.Validator.validateUserBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestRepository implements RequestDAO {
    private static DatabaseService databaseService = DatabaseServiceImpl.getInstance();

    @Override
    public List<Request> findAll() {
        List<Request> requests = new ArrayList<>();
        try (Connection connection = databaseService.takeConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM sql11188080.requests")) {
            while (resultSet.next()) {
                requests.add(
                        new Request(resultSet.getInt("id"), resultSet.getInt("number"), resultSet.getInt("id_user"),
                                resultSet.getInt("beds"), resultSet.getInt("id_class"),
                                resultSet.getDate("date_from"),
                                resultSet.getDate("date_to"), resultSet.getString("comments")));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return requests;
    }

    @Override
    public List<Request> findByUser(User user) {
        List<Request> requests = new ArrayList<>();
        ResultSet resultSet = null;
        if (validateUserBean(user)) {
            try (Connection connection = databaseService.takeConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(
                         "SELECT * FROM sql11188080.requests WHERE id_user = ?")) {
                preparedStatement.setInt(1, user.getId());
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    requests.add(
                            new Request(resultSet.getInt("id"), resultSet.getInt("number"), resultSet.getInt("id_user"),
                                    resultSet.getInt("beds"), resultSet.getInt("id_class"),
                                    resultSet.getDate("date_from"),
                                    resultSet.getDate("date_to"), resultSet.getString("comments"))
                    );
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return requests;
    }

    @Override
    public List<Request> findAllHandledRequests() {
        List<Request> handledRequests = new ArrayList<>();
        try (Connection connection = databaseService.takeConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT requests.number, requests.id_user, bills.number, bills.sum," +
                     " bills.paid, bills.created FROM sql11188080.requests INNER JOIN sql11188080.bills ON requests.id = bill.id_request;")) {

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Request findByNumber(int number) {
        if (number == 0) {
            return emptyRequest();
        }
        ResultSet resultSet = null;
        try (Connection connection = databaseService.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM sql11188080.requests WHERE number = ?")) {
            preparedStatement.setInt(1, number);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                return new Request(resultSet.getInt("id"),
                        resultSet.getInt("number"),
                        resultSet.getInt("id_user"),
                        resultSet.getInt("beds"),
                        resultSet.getInt("id_class"),
                        new java.util.Date(resultSet.getDate("date_from").getTime()),
                        new java.util.Date(resultSet.getDate("date_to").getTime()),
                        resultSet.getString("comments"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null && !resultSet.isClosed()) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return emptyRequest();
    }

    @Override
    public boolean insertRequest(Request request) {
        if (Validator.validateRequestBean(request)) {
            try (Connection connection = databaseService.takeConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(
                         "INSERT INTO `sql11188080`.`requests` (`number`, `id_user`, `beds`, `id_class`, `date_from`, `date_to`, `comments`) VALUES (?, ?, ?, ?, ?, ?, ?);")) {
                preparedStatement.setInt(1, request.getNumber());
                preparedStatement.setInt(2, request.getUserId());
                preparedStatement.setInt(3, request.getBeds());
                preparedStatement.setInt(4, request.getClassID());
                preparedStatement.setDate(5, new java.sql.Date(request.getDateFrom().getTime()));
                preparedStatement.setDate(6, new java.sql.Date((request.getDateTo().getTime())));
                preparedStatement.setString(7, request.getComments());
                return preparedStatement.executeUpdate() == 1 ? true : false;

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean updateRequest(Request request) {
        if (Validator.validateRequestBean(request)) {
            try (Connection connection = databaseService.takeConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(
                         "UPDATE `sql11188080`.`requests` SET `id_user` = ?, `beds` = ?, `id_class` = ?, `date_from` = ?, `date_to` = ?, `comments` = ? WHERE number = ?")) {
                preparedStatement.setInt(1, request.getUserId());
                preparedStatement.setInt(2, request.getBeds());
                preparedStatement.setInt(3, request.getClassID());
                preparedStatement.setDate(4, new java.sql.Date(request.getDateFrom().getTime()));
                preparedStatement.setDate(5, new java.sql.Date((request.getDateTo().getTime())));
                preparedStatement.setString(6, request.getComments());
                preparedStatement.setInt(7, request.getNumber());
                return preparedStatement.executeUpdate() == 1 ? true : false;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean deleteRequest(Request request) {
        if (Validator.validateRequestBean(request)) {
            try (Connection connection = databaseService.takeConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(
                         "DELETE FROM sql11188080.requests WHERE number = ?")) {
                preparedStatement.setInt(1, request.getNumber());
                return preparedStatement.executeUpdate() == 1 ? true : false;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    private Request emptyRequest() {
        return new Request();
    }

    public static int returnMaxRequestNumber() {
        ResultSet resultSet = null;
        try (Connection connection = databaseService.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT MAX(number) FROM sql11188080.requests ")) {
            resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                return resultSet.getInt("MAX(number)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null || !resultSet.isClosed()) {
                    resultSet.close();
                }
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
}
