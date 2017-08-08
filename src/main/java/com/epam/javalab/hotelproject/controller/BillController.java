package com.epam.javalab.hotelproject.controller;

import com.epam.javalab.hotelproject.model.Room;
import com.epam.javalab.hotelproject.service.RoomService;
import com.epam.javalab.hotelproject.service.RoomServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "billServlet",
        urlPatterns = {"/bill"}
)
public class BillController extends HttpServlet {
    RoomService roomService = new RoomServiceImpl();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String roomNumber = req.getParameter("roomNumber");
        System.out.println(roomNumber);
        req.getRequestDispatcher("/jsp/bill.jsp");
    }
}
