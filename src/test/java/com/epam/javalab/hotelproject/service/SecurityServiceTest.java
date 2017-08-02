package com.epam.javalab.hotelproject.service;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

public class SecurityServiceTest {
    SecurityService securityService = new SecurityService();
    @Test
    public void hash() throws Exception {
        String password = "asdfdhsdhsdfmgasmdglamsodmalsdmlakf33";
        String hashedPassword = securityService.hash(password);
        assertThat(hashedPassword, is(securityService.hash(password)));
        password = "aoijgojasodmfaspn21jsj";
        assertThat(hashedPassword, not(securityService.hash(password)));
    }


}