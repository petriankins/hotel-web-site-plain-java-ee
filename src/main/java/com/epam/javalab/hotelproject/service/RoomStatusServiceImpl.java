package com.epam.javalab.hotelproject.service;

import com.epam.javalab.hotelproject.model.Request;
import com.epam.javalab.hotelproject.model.Room;
import com.epam.javalab.hotelproject.model.RoomStatus;
import com.epam.javalab.hotelproject.repository.RoomStatusDAO;
import com.epam.javalab.hotelproject.repository.RoomStatusRepository;
import org.apache.log4j.Logger;

public class RoomStatusServiceImpl implements RoomStatusService {
    private final static Logger        LOGGER        = Logger.getLogger(RoomStatusServiceImpl.class);
    private final        RoomStatusDAO roomStatusDAO = new RoomStatusRepository();

    @Override
    public boolean save(RoomStatus roomStatus) {
        return roomStatusDAO.insert(roomStatus);
    }

    @Override
    public boolean edit(RoomStatus roomStatus) {
        return roomStatusDAO.update(roomStatus);
    }

    @Override
    public boolean delete(RoomStatus roomStatus) {
        return roomStatusDAO.delete(roomStatus);
    }

    @Override
    public boolean bookRoom(Request request, Room room) {
        return save(new RoomStatus(request.getUserId(), room.getId(), request.getDateFrom(), request.getDateTo()));
    }
}
