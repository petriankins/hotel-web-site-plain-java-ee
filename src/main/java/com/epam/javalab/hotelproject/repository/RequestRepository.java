package com.epam.javalab.hotelproject.repository;

import com.epam.javalab.hotelproject.model.Request;
import com.epam.javalab.hotelproject.model.User;
import com.epam.javalab.hotelproject.service.DatabaseService;
import com.epam.javalab.hotelproject.service.DatabaseServiceImpl;
import com.epam.javalab.hotelproject.utils.DateHelper;
import com.epam.javalab.hotelproject.utils.Validator;
import org.apache.log4j.Logger;

import static com.epam.javalab.hotelproject.utils.Validator.validateUserBean;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides API for finding, inserting, deleting, updating etc. requests in/from database.
 *
 * @author Iaichnikov Denis,
 * @version 1.0
 * @since 1.0
 */
public class RequestRepository implements RequestDAO {
    private final static Logger           LOGGER          = Logger.getLogger(RequestRepository.class);
    private final static DatabaseService  databaseService = DatabaseServiceImpl.getInstance();
    private static final String           TABLE_NAME      = "requests";
    private final        SimpleDateFormat sdf             = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    public List<Request> findAll() {
        List<Request> requests = new ArrayList<>();

        String query = "SELECT * FROM " + databaseService.getDatabaseName() + "." + TABLE_NAME;
        try (Connection connection = databaseService.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                requests.add(createRequest(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return requests;
    }

    @Override
    public List<Request> findByUser(User user) {
        List<Request> requests = new ArrayList<>();
        ResultSet resultSet = null;
        if (validateUserBean(user)) {
            String query =
                    "SELECT * FROM " + databaseService.getDatabaseName() + "." + TABLE_NAME + " WHERE id_user = ?";
            try (Connection connection = databaseService.takeConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, user.getId());
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    requests.add(createRequest(resultSet));
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
        }

        return requests;
    }

    @Override
    public List<Request> findAllUnhandledRequests() {
        List<Request> handledRequests = new ArrayList<>();

        String query = "SELECT " + TABLE_NAME + ".*, bills.number " + "FROM " + databaseService.getDatabaseName() +
                       "." + TABLE_NAME + " " + "LEFT JOIN bills ON " + TABLE_NAME + ".id = bills.id_request " +
                       "WHERE bills.id_request IS null;";

        try (Connection connection = databaseService.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                handledRequests.add(createRequest(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return handledRequests;
    }

    @Override
    public Request findByNumber(int number) {
        if (number == 0) {
            return emptyRequest();
        }

        ResultSet resultSet = null;
        String query = "SELECT * FROM sql11188080.requests WHERE number = ?";

        try (Connection connection = databaseService.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, number);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                return new Request(resultSet.getInt("id"),
                                   resultSet.getInt("number"),
                                   resultSet.getInt("id_user"),
                                   resultSet.getInt("beds"),
                                   resultSet.getInt("id_class"),
                                   DateHelper.javaToSQLDdate(resultSet.getDate("date_from")),
                                   DateHelper.javaToSQLDdate(resultSet.getDate("date_to")),
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
            String query =
                    "INSERT INTO `sql11188080`.`requests` (`number`, `id_user`, `beds`, `id_class`, `date_from`, `date_to`, `comments`) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?);";

            try (Connection connection = databaseService.takeConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, request.getNumber());
                preparedStatement.setInt(2, request.getUserId());
                preparedStatement.setInt(3, request.getBeds());
                preparedStatement.setInt(4, request.getClassID());
                preparedStatement.setDate(5, DateHelper.javaToSQLDdate(request.getDateFrom()));
                preparedStatement.setDate(6, DateHelper.javaToSQLDdate(request.getDateTo()));
                preparedStatement.setString(7, request.getComments());

                return preparedStatement.executeUpdate() == 1;
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }

        return false;
    }

    @Override
    public boolean updateRequest(Request request) {
        if (Validator.validateRequestBean(request)) {
            String query = "UPDATE `sql11188080`.`requests` " +
                           "SET `id_user` = ?, `beds` = ?, `id_class` = ?, `date_from` = ?, `date_to` = ?, `comments` = ? " +
                           "WHERE number = ?";
            try (Connection connection = databaseService.takeConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, request.getUserId());
                preparedStatement.setInt(2, request.getBeds());
                preparedStatement.setInt(3, request.getClassID());
                preparedStatement.setDate(4, DateHelper.javaToSQLDdate(request.getDateFrom()));
                preparedStatement.setDate(5, DateHelper.javaToSQLDdate(request.getDateTo()));
                preparedStatement.setString(6, request.getComments());
                preparedStatement.setInt(7, request.getNumber());

                return preparedStatement.executeUpdate() == 1;
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }

        return false;
    }

    @Override
    public boolean deleteRequest(Request request) {
        if (Validator.validateRequestBean(request)) {
            String billQuery = "DELETE FROM sql11188080.bills WHERE id_request = ?";
            String requestQuery = "DELETE FROM sql11188080.requests WHERE id = ?";
            try (Connection connection = databaseService.takeConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(billQuery);
                 PreparedStatement preparedStatement1 = connection.prepareStatement(requestQuery)) {
                connection.setAutoCommit(false);

                preparedStatement.setInt(1, request.getId());
                preparedStatement.executeUpdate();

                preparedStatement1.setInt(1, request.getId());
                boolean result = preparedStatement1.executeUpdate() == 1;

                connection.commit();
                connection.setAutoCommit(true);

                return result;
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }

        return false;
    }


    public static int returnMaxRequestNumber() {
        ResultSet resultSet = null;
        String query = "SELECT MAX(number) FROM " + databaseService.getDatabaseName() + "." + TABLE_NAME;

        try (Connection connection = databaseService.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            resultSet = preparedStatement.executeQuery();

            if (resultSet.first()) {
                return resultSet.getInt("MAX(number)");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                if (resultSet != null || !resultSet.isClosed()) {
                    resultSet.close();
                }
            } catch (SQLException | NullPointerException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }

        return 0;
    }

    private Request emptyRequest() {
        return new Request();
    }

    private Request createRequest(ResultSet resultSet) throws SQLException {
        return new Request(resultSet.getInt("id"),
                           resultSet.getInt("number"),
                           resultSet.getInt("id_user"),
                           resultSet.getInt("beds"),
                           resultSet.getInt("id_class"),
                           resultSet.getDate("date_from"),
                           resultSet.getDate("date_to"),
                           resultSet.getString("comments"));
    }
}
