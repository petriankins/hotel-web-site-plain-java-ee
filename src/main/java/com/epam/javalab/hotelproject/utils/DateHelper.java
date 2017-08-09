package com.epam.javalab.hotelproject.utils;


import java.util.Date;
import java.util.concurrent.TimeUnit;


public class DateHelper {

    public static int calculateDaysBetweenDates(Date dateFrom, Date dateTo)
    {
        long diff = dateTo.getTime() - dateFrom.getTime();
        return (int)TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
    public static java.sql.Date javaToSQLDdate(Date javaDate) {
        return new java.sql.Date(javaDate.getTime());
    }

}
