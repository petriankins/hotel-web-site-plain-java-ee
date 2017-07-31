package com.epam.javalab.hotelproject.model;

public class User {
    private int id;
    private String name;
    private String password;
    private String login;
    private String email;

    public User(String name, String password, String login, String email) {
        this.name = name;
        this.password = password;
        this.login = login;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
