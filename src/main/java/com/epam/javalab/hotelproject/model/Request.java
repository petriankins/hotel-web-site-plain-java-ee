package com.epam.javalab.hotelproject.model;

import java.util.Date;

public class Request {
    private int    id;
    private int    number;
    private int    userId;
    private int    beds;
    private int    classID;
    private Date   dateFrom;
    private Date   dateTo;
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

    public Request(int number, int userId, int beds, int classID, Date dateFrom, Date dateTo, String comments) {
        this.number = number;
        this.userId = userId;
        this.beds = beds;
        this.classID = classID;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.comments = comments;
    }

    public Request() {
        this(0, 0, 0, 0, new Date(), new Date(), "");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
