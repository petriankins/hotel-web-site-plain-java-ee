package com.epam.javalab.hotelproject.model;

import java.util.Date;

public class Request {
    private int id;
    private int number;
    private int userId;
    private int beds;
    private int classID;
    private Date dateFrom;
    private Date dateTo;
    private String comments;

    public Request(int id, int number, int userId, int beds, int classID, Date dateFrom, Date dateTo, String comments) {
        this.id = id;
        this.number = number;
        this.userId = userId;
        this.beds = beds;
        this.classID = classID;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.comments = comments;
    }

    public Request() {

    }
}
