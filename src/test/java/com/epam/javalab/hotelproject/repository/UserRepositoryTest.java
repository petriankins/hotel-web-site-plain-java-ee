package com.epam.javalab.hotelproject.repository;

import com.epam.javalab.hotelproject.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

@Ignore
public class UserRepositoryTest {
    UserDAO userDAO = new UserRepository();

    Map<String, User> users;

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
        if (!user1.getLogin().equals(user2.getLogin())) return false;
        if (!user1.getPassword().equals(user2.getPassword())) return false;
        if (!user1.getName().equals(user2.getName())) return false;
        if (!user1.getLastName().equals(user2.getLastName())) return false;

        return true;
    }

    @After
    public void tearDown() throws Exception {
        users.forEach((k, v) -> userDAO.deleteUser(v));
    }

    @Test
    public void findAll() throws Exception {
        List<User> allUsers = userDAO.findAll();
        assertThat(allUsers.size(), is((users.size() + 1)));
        for (User user : allUsers) {
            assertThat(users.get(user.getLogin()), not(null));
            assertThat(compareUsers(users.get(user.getLogin()), user), is(true));
        }
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