package com.epam.javalab.hotelproject.repository;

import com.epam.javalab.hotelproject.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

public class UserRepositoryTest {
    private final UserDAO userDAO = new UserRepository();

    private Map<String, User> users;

    @Before
    public void setUp() throws Exception {
        users = new HashMap<>();
        addUserToMap(users, new User("Vasya", "Pupkin", "vasya@cool.ru", "MyPassword"));
        addUserToMap(users, new User("Nikitia", "Popkin", "nikitos@cool.ru", "nikitaKRUT"));
        addUserToMap(users, new User("Vika", "Ivanova", "nikavika@cool.ru", "VikaBarbie"));
        users.forEach((k, v) -> userDAO.insertUser(v));
    }

    private void addUserToMap(Map<String, User> map, User user) {
        map.put(user.getLogin(), user);
    }

    private boolean compareUsers(User user1, User user2) {
        if (!user1.getLogin().equals(user2.getLogin())) {
            printDifferences("Logins", user1.getLogin(), user2.getLogin());
            return false;
        }
        if (!user1.getPassword().equals(user2.getPassword())) {
            printDifferences("Passwords", user1.getPassword(), user2.getPassword());
            return false;
        }
        if (!user1.getName().equals(user2.getName())) {
            printDifferences("Names", user1.getName(), user2.getName());
            return false;
        }
        if (!user1.getLastName().equals(user2.getLastName())) {
            printDifferences("Last names", user1.getLastName(), user2.getLastName());
            return false;
        }

        return true;
    }

    private void printDifferences(String field, String expected, String actual) {
        System.out.println(field + " does not match");
        System.out.println("Expected: " + expected);
        System.out.println("Actual: " + actual);
    }

    @After
    public void tearDown() throws Exception {
        users.forEach((k, v) -> userDAO.deleteUser(v));
    }

    @Test
    public void findAll() throws Exception {
        List<User> allUsers = userDAO.findAll();
        int foundUsers = 0;
        for (User user : allUsers) {
            assertThat(users.getOrDefault(user.getLogin(), user), notNullValue());
            assertThat(compareUsers(users.getOrDefault(user.getLogin(), user), user), is(true));
            if (users.get(user.getLogin()) != null) foundUsers++;
        }
        assertThat(foundUsers, is(users.size()));
    }

    @Test
    public void findByLogin() throws Exception {
        users.forEach((k, v) -> {
            User user = userDAO.findByLogin(v.getLogin());
            assertThat(compareUsers(v, user), is(true));
        });
    }

    @Test
    public void insertUser() throws Exception {
        User newUser = makeTestUser();
        assertThat(userDAO.insertUser(newUser), is(true));
        User storedUser = userDAO.findByLogin(newUser.getLogin());
        assertThat(compareUsers(newUser, storedUser), is(true));
        assertThat(storedUser.getId(), not(0));
        userDAO.deleteUser(newUser);
    }

    private User makeTestUser() {
        return new User("Kolayn4ik", "4etkiy", "kolya@yandex.ru", "kolyavsemoget");
    }

    @Test
    public void updateUser() throws Exception {
        users.forEach((k, v) -> {
            v.setName("We all clones now");
            assertThat(userDAO.updateUser(v), is(true));
        });
        users.forEach((k, v) -> assertThat(compareUsers(userDAO.findByLogin(v.getLogin()), v), is(true)));
    }

    @Test
    public void deleteUser() throws Exception {
        User newUser = makeTestUser();
        userDAO.insertUser(newUser);
        assertThat(compareUsers(userDAO.findByLogin(newUser.getLogin()), newUser), is(true));
        assertThat(userDAO.deleteUser(newUser), is(true));
        assertThat(compareUsers(userDAO.findByLogin(newUser.getLogin()), newUser), is(false));
    }
}