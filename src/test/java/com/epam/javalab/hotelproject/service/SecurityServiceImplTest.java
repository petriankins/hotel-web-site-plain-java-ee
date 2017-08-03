package com.epam.javalab.hotelproject.service;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

public class SecurityServiceImplTest {
    SecurityServiceImpl securityServiceImpl = new SecurityServiceImpl();
    @Test
    public void hash() throws Exception {
        String password = "asdfdhsdhsdfmgasmdglamsodmalsdmlakf33";
        String hashedPassword = securityServiceImpl.hash(password);
        assertThat(hashedPassword, is(securityServiceImpl.hash(password)));
        password = "aoijgojasodmfaspn21jsj";
        assertThat(hashedPassword, not(securityServiceImpl.hash(password)));
    }


}