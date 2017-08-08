package com.epam.javalab.hotelproject.model;

import java.util.Date;

public class Bill {
    private int id;
    private int number;
    private int sum;
    private int paid;
    private int idRequest;
    private Date dateOfCreation;
    private int idRoom;

    public Bill(int id, int number, int sum, int paid, int idRequest, Date dateOfCreation, int idRoom) {
        this.id = id;
        this.number = number;
        this.sum = sum;
        this.paid = paid;
        this.idRequest = idRequest;
        this.dateOfCreation = dateOfCreation;
        this.idRoom = idRoom;
    }

    public Bill(int number, int sum, int paid, int idRequest, Date dateOfCreation, int idRoom) {
        this.number = number;
        this.sum = sum;
        this.paid = paid;
        this.idRequest = idRequest;
        this.dateOfCreation = dateOfCreation;
        this.idRoom = idRoom;
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

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    public int getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(int idRequest) {
        this.idRequest = idRequest;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }
}
