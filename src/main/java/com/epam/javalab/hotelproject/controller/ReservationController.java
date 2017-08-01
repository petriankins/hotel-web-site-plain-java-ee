package com.epam.javalab.hotelproject.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;

@WebServlet(
        name = "ReservationServlet",
        urlPatterns = { "/order"}
)
public class ReservationController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/order.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/order.jsp").forward(req, resp);
        int beds = Integer.parseInt(req.getParameter("beds"));
        int stars = Integer.parseInt(req.getParameter("stars"));
        //SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        //date.format(req.getParameter("checkIn"));
        String checkIn = req.getParameter("checkIn");
        String checkOut = req.getParameter("checkOut");

        System.out.println(beds + stars + checkIn + checkOut);

    }
}
