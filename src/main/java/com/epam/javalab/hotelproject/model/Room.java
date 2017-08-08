package com.epam.javalab.hotelproject.model;


import java.util.Date;

public class Room {
    private int id;
    private int id_class;
    private int number;
    private int beds;
    private int roomClass;
    private Date dateFrom;
    private Date dateTo;

    public Room(int number, int beds, int roomClass, Date dateFrom, Date dateTo) {
        this.number = number;
        this.beds = beds;
        this.roomClass = roomClass;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public Room(int id, int number, int beds, int roomClass) {
        this.id = id;
        this.number = number;
        this.beds = beds;
        this.roomClass = roomClass;
    }

    public Room(int number, int beds, int roomClass) {
        this.number = number;
        this.beds = beds;
        this.roomClass = roomClass;
    }

    public int getId_class() {
        return id_class;
    }

    public void setId_class(int id_class) {
        this.id_class = id_class;
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

    public Room() {
        this(0, 0, 0, 0);
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

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public int getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(int roomClass) {
        this.roomClass = roomClass;
    }
}
