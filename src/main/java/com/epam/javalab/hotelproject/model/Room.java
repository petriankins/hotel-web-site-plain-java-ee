package com.epam.javalab.hotelproject.model;


public class Room {
    private int id;
    private int number;
    private int beds;
    private int roomClass;
    boolean isFree;

    public Room(int id, int number, int beds, int roomClass) {
        this.id = id;
        this.number = number;
        this.beds = beds;
        this.roomClass = roomClass;
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
