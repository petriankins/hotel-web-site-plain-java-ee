package com.epam.javalab.hotelproject.service;

import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.Assert.*;

public class DataBaseServiceTest {
    DataBaseService dataBaseService = DataBaseService.getInstance();

    @Test
    public void test() throws Exception {
        Connection connection = dataBaseService.takeConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM sql11188080.test");
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2));
        }
    }
}