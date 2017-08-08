package com.epam.javalab.hotelproject.controller;

import com.epam.javalab.hotelproject.model.Request;
import com.epam.javalab.hotelproject.model.Room;
import com.epam.javalab.hotelproject.service.RequestService;
import com.epam.javalab.hotelproject.service.RequestServiceImpl;
import com.epam.javalab.hotelproject.service.RoomService;
import com.epam.javalab.hotelproject.service.RoomServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(
        name = "AppointRoomServlet",
        urlPatterns = {"/appointRoom"}
)
public class AppointRoomController extends HttpServlet {
    RequestService requestService = new RequestServiceImpl();
    RoomService roomService = new RoomServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String number = req.getParameter("requestNumber").trim();
        System.out.println(number);
        Request request = requestService.findByNumber(Integer.parseInt(number));
        req.setAttribute("request", request);

        List<Room> availableRooms = roomService.getAvailableRooms(request);
        System.out.println(availableRooms);
//
//        List<Room> availableRooms = new ArrayList<>();
//        availableRooms.add(new Room(1, 1, 1, 1));
//        availableRooms.add(new Room(2, 2, 2, 2));
//        availableRooms.add(new Room(3, 3, 3, 3));
//        availableRooms.add(new Room());

        req.setAttribute("availableRooms", availableRooms);
        req.getRequestDispatcher("/jsp/appointRoom.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/bill");

    }
}
