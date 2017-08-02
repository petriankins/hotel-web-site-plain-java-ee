package com.epam.javalab.hotelproject.service;


import java.sql.Connection;

public interface DatabaseService {
    Connection takeConnection();

    void closeConnection(Connection connection);

    void dispose();
}