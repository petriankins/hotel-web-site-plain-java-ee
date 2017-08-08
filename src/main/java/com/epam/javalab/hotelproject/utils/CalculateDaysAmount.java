package com.epam.javalab.hotelproject.utils;


import java.util.Date;


public class CalculateDaysAmount {
    public static int calculateDaysBetweenDates(Date dateFrom, Date dateTo)

    {
        long diff = dateTo.getTime() - dateFrom.getTime();
        return (int) diff;

    }
}
