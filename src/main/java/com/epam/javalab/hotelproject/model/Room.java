package com.epam.javalab.hotelproject.model;

public class Room {
    private int id;
    private int number;
    private int beds;
    private RoomClass roomClass;
    boolean isFree;

    public Room(int number, int beds, RoomClass roomClass) {
        this.number = number;
        this.beds = beds;
        this.roomClass = roomClass;
    }

    public Room(int number, int beds, RoomClass roomClass, boolean isFree) {
        this.number = number;
        this.beds = beds;
        this.roomClass = roomClass;
        this.isFree = isFree;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
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

    public RoomClass getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(RoomClass roomClass) {
        this.roomClass = roomClass;
    }
}
