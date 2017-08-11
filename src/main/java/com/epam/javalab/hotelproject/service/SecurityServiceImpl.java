package com.epam.javalab.hotelproject.service;

import com.epam.javalab.hotelproject.model.Request;
import com.epam.javalab.hotelproject.model.User;
import com.epam.javalab.hotelproject.repository.UserDAO;
import com.epam.javalab.hotelproject.repository.UserRepository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * Provides API fo hashing password and authorization
 *
 * @author Maksim Starshinov, Sergei Petriankin
 * @version 1.0
 */

public class SecurityServiceImpl implements SecurityService {
    private final UserDAO userDAO = new UserRepository();

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
    @Override
    public boolean authenticate(User user) {
        User registeredUser = userDAO.findByLogin(user.getLogin());
        if (registeredUser.getLogin().isEmpty()) {
            return false;
        }

        if (comparePasswords(user.getPassword(), registeredUser.getPassword())) {
            user.setId(registeredUser.getId());
            user.setName(registeredUser.getName());
            user.setLastName(registeredUser.getLastName());

            return true;
        }
        return false;
    }

    /**
     *
     * @param user <code>user</code> bean for authorize check
     * @param request <code>request</code> bean for authorize check
     * @return <code>true</code> if user can access provided request, otherwise will return <code>false</code>
     */
    @Override
    public boolean authorize(User user, Request request) {
        if (user.getLogin().equals("info@hotel.project")) return true;
        return user.getId() == request.getUserId();
    }

    /**
     * Compares to given password
     *
     * @param inputPassword  password from user input on web page
     * @param storedPassword password retrieved from database
     * @return <code>true</code> if passwords match, otherwise returns <code>false</code>
     */
    private boolean comparePasswords(String inputPassword, String storedPassword) {
        int i = 0;
        byte[] inputPasswordBytes = inputPassword.getBytes();
        for (byte b : storedPassword.getBytes()) {
            if (b != inputPasswordBytes[i++]) return false;
        }

        return true;
    }
}