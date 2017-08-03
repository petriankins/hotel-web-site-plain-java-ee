package com.epam.javalab.hotelproject.service;

import com.epam.javalab.hotelproject.model.User;
import com.epam.javalab.hotelproject.repository.UserDAO;
import com.epam.javalab.hotelproject.repository.UserRepository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;

/**
 * Provides API fo hashing password and suthorization
 *
 * @author Maksim Starshinov, Sergei Petriankin
 * @version 1.0
 */

public class SecurityServiceImpl implements SecurityService {
    private UserDAO userDAO = new UserRepository();

    /**
     * @param password
     * @return hashed password using MD5 algorithm
     */
    public String hash(String password) {
        String result = password;
        try {
            byte[] bytesOfMessage = password.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] theDigest = md.digest(bytesOfMessage);
            result = new String(theDigest, StandardCharsets.UTF_8);
        } catch (Exception e) {
            //TODO log4j
        }
        return result;
    }

    /**
     * @param user
     * @return <code>true</code> if such user is is already registered
     */
    public boolean authorize(User user) {
        User registeredUser = userDAO.findByLogin(user.getLogin());
        if (registeredUser.getLogin().isEmpty()) {
            return false;
        }
        return hash(user.getPassword()).equals(registeredUser.getPassword());


    }
}