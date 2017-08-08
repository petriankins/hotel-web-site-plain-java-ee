package com.epam.javalab.hotelproject.model;


import java.util.Date;

public class Room {
    private int id;
    private int id_roomstatus;
    private int id_user;
    private int number;
    private int beds;
    private int roomClass;
    private Date dateFrom;
    private Date dateTo;

    public Room(int id_roomstatus, int id_user, int id, Date dateFrom, Date dateTo) {
        this.id = id;
        this.id_roomstatus = id_roomstatus;
        this.id_user = id_user;
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


    public int getId_roomstatus() {
        return id_roomstatus;
    }

    public void setId_roomstatus(int id_roomstatus) {
        this.id_roomstatus = id_roomstatus;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
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
