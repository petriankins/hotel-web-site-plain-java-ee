package com.epam.javalab.hotelproject.service;

import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataBaseServiceImplTest {
    DataBaseServiceImpl dataBaseServiceImpl = DataBaseServiceImpl.getInstance();

    @Test
    public void test() throws Exception {
        Connection connection = dataBaseServiceImpl.takeConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM sql11188080.test");
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2));
        }
    }
}