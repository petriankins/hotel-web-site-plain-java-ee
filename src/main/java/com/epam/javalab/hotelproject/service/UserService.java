package com.epam.javalab.hotelproject.service;

import com.epam.javalab.hotelproject.model.User;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class UserService {

    public boolean registerUser(User user) {
        // TODO
        if (loginIsAvailable("")) {

        }
        throw new NotImplementedException();
    }

    public boolean authorize(User user) {
        SecurityService securityService = new SecurityService();
        throw new NotImplementedException();
    }

    private boolean loginIsAvailable(String login) {
        // TODO
        getUserByLogin(login);
        throw new NotImplementedException();
    }

    private User getUserByLogin(String login) {
        throw new NotImplementedException();
    }
}