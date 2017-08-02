package com.epam.javalab.hotelproject.service;

import com.epam.javalab.hotelproject.model.User;
import com.epam.javalab.hotelproject.repository.UserDAO;
import com.epam.javalab.hotelproject.repository.UserRepository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.security.MessageDigest;
import java.util.Arrays;

public class SecurityService {
    private UserDAO userDAO = new UserRepository();

    public String hash(String password) {
        String result = password;
        try {
            byte[] bytesOfMessage = password.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] theDigest = md.digest(bytesOfMessage);
            result = Arrays.toString(theDigest);
        } catch (Exception e) {
            //TODO log4j
        }
        return result;
    }

    public boolean authorize(User user) {
        User registeredUser = userDAO.findByLogin(user.getLogin());
        if (registeredUser.getLogin().isEmpty()) {
            return false;
        }

        return hash(user.getPassword()).equals(registeredUser.getPassword());
    }
}