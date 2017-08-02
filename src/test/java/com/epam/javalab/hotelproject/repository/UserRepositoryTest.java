package com.epam.javalab.hotelproject.repository;

import com.epam.javalab.hotelproject.model.User;
import com.epam.javalab.hotelproject.service.DatabaseService;
import com.epam.javalab.hotelproject.service.DatabaseServiceImpl;
import com.epam.javalab.hotelproject.service.DatabaseService;
import com.epam.javalab.hotelproject.service.DatabaseServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserRepositoryTest {
    private DatabaseService databaseService = DatabaseServiceImpl.getInstance();
    private UserDAO         userDAO         = new UserRepository();

    public User createReferenceUser() {
        return new User("my name", "my last name", "aaa@aaa.com", "mypassword");
    }

    @Before
    public void insertUsers() {
        try (Connection connection = databaseService.takeConnection();
             Statement statement = connection.createStatement();) {
            statement.executeUpdate(
                    "INSERT INTO `sql11188080`.`users` (`email`, `password`, `first_name`, `last_name`) VALUES ('aaa@aaa.com','mypassword','my name', 'my last name');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @After
    public void clearUsers() {
        try (Connection connection = databaseService.takeConnection();
             Statement statement = connection.createStatement();) {
            statement.executeUpdate("DELETE FROM sql11188080.users WHERE email != 'info@hotel.project';");
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
        User referenceUser = createReferenceUser();
        User user = userDAO.findByLogin("aaa@aaa.com");
        assertThat(user.getLogin(), is(referenceUser.getLogin()));
        assertThat(user.getPassword(), is(referenceUser.getPassword()));
        assertThat(user.getName(), is(referenceUser.getName()));
    }

    @Test
    public void insertUser() throws Exception {
        User newUser = new User("new name", "new last name", "new login", "new password");
        assertThat(userDAO.insertUser(newUser), is(true));
        User user = userDAO.findByLogin("new login");
        assertThat(user.getName(), is(newUser.getName()));
        assertThat(user.getLastName(), is(newUser.getLastName()));
        assertThat(user.getLogin(), is(newUser.getLogin()));
        assertThat(user.getPassword(), is(newUser.getPassword()));
    }

    @Test
    public void updateUser() throws Exception {
        User referenceUser = createReferenceUser();
        referenceUser.setPassword("myNewPassword");
        assertThat(userDAO.updateUser(referenceUser), is(true));
        assertThat(referenceUser.getPassword(), is(userDAO.findByLogin(referenceUser.getLogin()).getPassword()));
    }

    @Test
    public void deleteUser() throws Exception {
        User referenceUser = createReferenceUser();
        assertThat(userDAO.deleteUser(referenceUser), is(true));
        assertThat(userDAO.findByLogin(referenceUser.getLogin()).getLogin(), is((new User()).getLogin()));
    }
}