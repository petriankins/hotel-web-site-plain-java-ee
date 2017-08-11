package com.epam.javalab.hotelproject.repository;

import com.epam.javalab.hotelproject.model.RoomStatus;
import com.epam.javalab.hotelproject.service.DatabaseService;
import com.epam.javalab.hotelproject.service.DatabaseServiceImpl;
import com.epam.javalab.hotelproject.utils.DateHelper;
import com.epam.javalab.hotelproject.utils.Validator;
import org.apache.log4j.Logger;
import sun.rmi.runtime.Log;

import javax.activation.DataHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.javalab.hotelproject.utils.DateHelper.*;

public class RoomStatusRepository implements RoomStatusDAO {
    private final static Logger          LOGGER          = Logger.getLogger(RoomStatusRepository.class);
    private final static String          TABLE_NAME      = "roomstatus";
    private final        DatabaseService databaseService = DatabaseServiceImpl.getInstance();

    @Override
    public List<RoomStatus> findAll() {
        List<RoomStatus> roomStatuses = new ArrayList<>();
        ResultSet rs = null;
        try (Connection con = databaseService.takeConnection();
             PreparedStatement ps = con.prepareStatement(
                     "SELECT * FROM " + databaseService.getDatabaseName() + "." + TABLE_NAME)) {
            rs = ps.executeQuery();
            while (rs.next()) {
                roomStatuses.add(buildRoomStatus(rs));
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

        return roomStatuses;
    }

    private RoomStatus buildRoomStatus(ResultSet rs) throws SQLException {
        return new RoomStatus(rs.getInt("id"), rs.getInt("id_user"), rs.getInt("id_room"),
                              rs.getDate("date_from"), rs.getDate("date_to"));
    }

    @Override
    public RoomStatus findById(int id) {
        if (id == 0) {
            return emptyRoomStatus();
        }

        ResultSet rs = null;
        try (Connection con = databaseService.takeConnection();
             PreparedStatement ps = con.prepareStatement(
                     "SELECT * FROM " + databaseService.getDatabaseName() + "." + TABLE_NAME + " WHERE id=?")) {
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.first()) {
                return buildRoomStatus(rs);
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

        return emptyRoomStatus();
    }

    @Override
    public RoomStatus findByRoomId(int roomId) {
        if (roomId == 0) {
            return emptyRoomStatus();
        }

        ResultSet rs = null;
        try (Connection con = databaseService.takeConnection();
             PreparedStatement ps = con.prepareStatement(
                     "SELECT * FROM " + databaseService.getDatabaseName() + "." + TABLE_NAME + " WHERE id_room=?")) {
            ps.setInt(1, roomId);
            rs = ps.executeQuery();

            if (rs.first()) {
                return buildRoomStatus(rs);
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

        return emptyRoomStatus();
    }

    @Override
    public boolean insert(RoomStatus roomStatus) {
        if (!Validator.validateRoomStatusBean(roomStatus)) return false;

        try (Connection con = databaseService.takeConnection();
             PreparedStatement ps = con.prepareStatement(
                     "INSERT INTO " + databaseService.getDatabaseName() + "." + TABLE_NAME +
                     "(id_user, id_room, date_from, date_to) VALUES(?,?,?,?)")) {
            ps.setInt(1, roomStatus.getUserId());
            ps.setInt(2, roomStatus.getRoomId());
            ps.setDate(3, javaToSQLDdate(roomStatus.getDateFrom()));
            ps.setDate(4, javaToSQLDdate(roomStatus.getDateTo()));

            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return false;
    }

    @Override
    public boolean update(RoomStatus roomStatus) {
        if (!Validator.validateRoomStatusBean(roomStatus)) return false;

        try (Connection con = databaseService.takeConnection();
             PreparedStatement ps = con.prepareStatement(
                     "UPDATE " + databaseService.getDatabaseName() + "." + TABLE_NAME +
                     " SET id_user = ?, id_room = ?, date_from = ?, date_to = ? WHERE id = ?")) {
            ps.setInt(1, roomStatus.getUserId());
            ps.setInt(2, roomStatus.getRoomId());
            ps.setDate(3, javaToSQLDdate(roomStatus.getDateFrom()));
            ps.setDate(4, javaToSQLDdate(roomStatus.getDateTo()));

            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return false;
    }

    @Override
    public boolean delete(RoomStatus roomStatus) {
        if (!Validator.validateRoomStatusBean(roomStatus)) return false;

        try (Connection con = databaseService.takeConnection();
             PreparedStatement ps = con.prepareStatement(
                     "DELETE FROM " + databaseService.getDatabaseName() + "." + TABLE_NAME + " WHERE id = ?")) {
            ps.setInt(1, roomStatus.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private RoomStatus emptyRoomStatus() {
        return new RoomStatus();
    }
}
