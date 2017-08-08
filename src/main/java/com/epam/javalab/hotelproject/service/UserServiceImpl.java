package com.epam.javalab.hotelproject.service;

import com.epam.javalab.hotelproject.model.User;
import com.epam.javalab.hotelproject.repository.UserDAO;
import com.epam.javalab.hotelproject.repository.UserRepository;
import org.apache.log4j.Logger;

import static com.epam.javalab.hotelproject.utils.Validator.validateUserBean;

/**
 * Provides API for actions with users such as: Register, Authorize
 *
 * @author Maksim Starshinov, Sergei Petriankin
 * @version 1.0
 * @since 1.0
 */
public class UserServiceImpl implements UserService {
    private static final Logger          LOGGER          = Logger.getLogger(UserServiceImpl.class);
    private final        SecurityService securityService = new SecurityServiceImpl();
    private final        UserDAO         userDAO         = new UserRepository();

    /**
     * @param user bean from user input
     * @return <code>true</code> if registration was successful, otherwise <code>false</code>
     */
    public boolean registerUser(User user) {
        if (!validateUserBean(user)) {
            return false;
        }

        if (!loginIsAvailable(user.getLogin())) {
            LOGGER.info("User with email \"" + user.getLogin() + "\" already registered!");

            return false;
        }

        user.setPassword(securityService.hash(user.getPassword()));

        return saveUser(user);
    }

    /**
     * @param user bean from user input
     * @return <code>true</code> if authorization was successful, otherwise, if login and password didn't match <code>false</code>
     */
    public boolean authenticate(User user) {
        return validateUserBean(user) && securityService.authenticate(user);

    }

    private boolean saveUser(User user) {
        return userDAO.insertUser(user);
    }

    private boolean loginIsAvailable(String login) {
        return userDAO.findByLogin(login).getLogin().isEmpty();
    }

}