package com.epam.javalab.hotelproject.repository;

import com.epam.javalab.hotelproject.model.User;
import com.epam.javalab.hotelproject.service.DataBaseService;
import com.epam.javalab.hotelproject.service.DataBaseServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserRepositoryTest {
    private DataBaseService dataBaseService = DataBaseServiceImpl.getInstance();
    private UserDAO userDAO = new UserRepository();
    public static final User DEFAULT_USER = new User("my name", "my last name", "aaa@aaa.com", "mypassword");

    @Before
    public void insertUsers() {
        try (Connection connection = dataBaseService.takeConnection();
             Statement statement = connection.createStatement();) {
            statement.executeUpdate("INSERT INTO `sql11188080`.`users` (`email`, `password`, `first_name`, `last_name`) VALUES ('aaa@aaa.com','mypassword','my name', 'my last name');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @After
    public void clearUsers() {
        try (Connection connection = dataBaseService.takeConnection();
             Statement statement = connection.createStatement();) {
            statement.executeUpdate("DELETE FROM sql11188080.users;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAllUsers() throws Exception {
        List<User> users = userDAO.findAll();
        assertThat(users.size(), is(1));
    }

    @Test
    public void findByLogin() throws Exception {
        User user = userDAO.findByLogin("aaa@aaa.com");
        assertThat(user.getLogin(), is(DEFAULT_USER.getLogin()));
        assertThat(user.getPassword(), is(DEFAULT_USER.getPassword()));
        assertThat(user.getName(), is(DEFAULT_USER.getName()));
    }

    @Test
    public void insertUser() throws Exception {
        assertThat(userDAO.insertUser(new User("new name", "new last name", "new login", "new password")), is(true));


    }
}