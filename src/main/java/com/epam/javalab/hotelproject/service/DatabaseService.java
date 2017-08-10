package com.epam.javalab.hotelproject.service;

import java.sql.Connection;

/**
 * Provides API for taking, closing and disposing connection to DB
 *
 * @author Maksim Starshinov, Sergei Petriankin
 * @version 1.0
 */
public interface DatabaseService {
    Connection takeConnection();

    void closeConnection(Connection connection);

    void dispose();

    String getDatabaseName();
}