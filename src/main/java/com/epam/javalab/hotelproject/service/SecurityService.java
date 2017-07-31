package com.epam.javalab.hotelproject.service;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.security.MessageDigest;
import java.util.Arrays;

public class SecurityService {
    public String hash(String password) {
        String result = password;
        try {
            byte[] bytesOfMessage = password.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] theDigest = md.digest(bytesOfMessage);
            result = Arrays.toString(theDigest);
        }
        catch (Exception e) {
            //TODO log4j
        }
        return result;
    }
}
