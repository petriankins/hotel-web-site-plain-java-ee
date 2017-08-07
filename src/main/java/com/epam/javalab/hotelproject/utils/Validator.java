package com.epam.javalab.hotelproject.utils;

import com.epam.javalab.hotelproject.model.Request;
import com.epam.javalab.hotelproject.model.Room;
import com.epam.javalab.hotelproject.model.User;

public class Validator {
    /**
     * Checks if the user has login and password fields completed.
     *
     * @param user
     * @return <code>true</code> if user has login and password fields
     */
    public static boolean validateUserBean(User user) {
        if (user.getLogin() == null || user.getPassword() == null) {
            return false;
        }

        if (user.getLogin().isEmpty() || user.getPassword().isEmpty()) {
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
            return false;
        }
        return true;
    }

    /**
     * Checks if the room class, room number and amount of beds is specified
     * @param room
     * @return <code>true</code> if room has all the necessary parameters
     */
    public static boolean validateRoomBean(Room room) {
        if (room.getNumber() == 0 || room.getBeds() == 0 || room.getRoomClass() == 0) {
            return false;
        }
        return true;
    }
}
