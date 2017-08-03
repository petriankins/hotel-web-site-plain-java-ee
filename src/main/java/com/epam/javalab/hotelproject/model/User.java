package com.epam.javalab.hotelproject.model;

public class User {
    private int id;
    private String name;
    private String login;
    private String password;
    private String lastName;

    public User(String name, String lastName, String login, String password) {
        this.name = name;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
    }

    public User(int id, String name, String login, String password, String lastName) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.lastName = lastName;
    }

    public User() {
        this("","","","");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
