package com.epam.javalab.hotelproject.service;

import com.epam.javalab.hotelproject.model.User;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class UserService {
    private SecurityService securityService = new SecurityService();

    public boolean registerUser(User user) {
        if (!validateUserBean(user)) {
            return false;
        }

        if (!loginIsAvailable(user.getLogin())) {
            return false;
        }

        user.setPassword(securityService.hash(user.getPassword()));

        return saveUser(user);
    }

    public boolean authorize(User user) {
        throw new NotImplementedException();
    }

    private boolean saveUser(User user) {
        throw new NotImplementedException();
    }

    private boolean loginIsAvailable(String login) {
        // TODO
        getUserByLogin(login);
        throw new NotImplementedException();
    }

    private boolean validateUserBean(User user) {
        if (user.getLogin() == null || user.getPassword() == null || user.getEmail() == null) {
            return false;
        }

        if (user.getLogin().isEmpty() || user.getPassword().isEmpty() || user.getEmail().isEmpty()) {
            return false;
        }

        return true;
    }

    private User getUserByLogin(String login) {
        throw new NotImplementedException();
    }
}