package com.epam.javalab.hotelproject.utils;

import com.epam.javalab.hotelproject.model.User;

public class Validator {

    public static boolean validateUserBean(User user) {
        if (user.getLogin() == null || user.getPassword() == null) {
            return false;
        }

        if (user.getLogin().isEmpty() || user.getPassword().isEmpty()) {
            return false;
        }

        return true;
    }
}
