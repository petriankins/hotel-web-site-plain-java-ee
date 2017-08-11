package com.epam.javalab.hotelproject.model;

import java.util.Date;

public class RoomStatus {
    private int  id;
    private int  userId;
    private int  roomId;
    private Date dateFrom;
    private Date dateTo;

    public RoomStatus(int id, int userId, int roomId, Date dateFrom, Date dateTo) {
        this.id = id;
        this.userId = userId;
        this.roomId = roomId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public RoomStatus(int userId, int roomId, Date dateFrom, Date dateTo) {
        this(0, userId, roomId, dateFrom, dateTo);
    }

    public RoomStatus() {
        this(0, 0, new Date(), new Date());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
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
}
