package com.epam.javalab.hotelproject.utils;

import com.epam.javalab.hotelproject.model.Request;
import com.epam.javalab.hotelproject.model.Room;
import com.epam.javalab.hotelproject.model.RoomStatus;
import com.epam.javalab.hotelproject.model.User;
import org.apache.log4j.Logger;

public class Validator {
    private static final Logger LOGGER = Logger.getLogger(Validator.class);

    /**
     * Checks if the user has login and password fields completed.
     *
     * @param user
     * @return <code>true</code> if user has login and password fields
     */
    public static boolean validateUserBean(User user) {
        if (user.getLogin() == null) {
            LOGGER.error("Couldn't validate user bean! Login is null!");

            return false;
        }
        if (user.getPassword() == null) {
            LOGGER.error("Couldn't validate user bean! Password is null!");

            return false;
        }

        if (user.getLogin().isEmpty()) {
            LOGGER.error("Couldn't validate user bean! Login is empty!");

            return false;
        }
        if (user.getPassword().isEmpty()) {
            LOGGER.error("Couldn't validate user bean! Password is empty!");

            return false;
        }

        return true;
    }

    /**
     * Checks if the request has the number
     *
     * @param request
     * @return <code>true</code> if the number of request doesn't equal 0
     */
    public static boolean validateRequestBean(Request request) {
        if (request.getNumber() == 0) {
            LOGGER.error("Couldn't validate request bean. Request for user with id[" + request.getUserId() +
                         "] didn't have a number!");

            return false;
        }
        return true;
    }

    /**
     * Checks if the room class, room number and amount of beds is specified
     *
     * @param room
     * @return <code>true</code> if room has all the necessary parameters
     */
    public static boolean validateRoomBean(Room room) {
        if (room.getNumber() == 0) {
            LOGGER.error("Couldn't validate room bean! Room number is not specified!");

            return false;
        }

        if (room.getBeds() == 0) {
            LOGGER.error("Couldn't validate room bean! Amount of beds is not specified!");

            return false;
        }

        if (room.getRoomClass() == 0) {
            LOGGER.error("Couldn't validate room bean! Room class is not specified!");

            return false;
        }

        return true;
    }

    // TODO
    public static boolean validateRoomStatusBean(RoomStatus roomStatus) {
        return true;
    }
}
