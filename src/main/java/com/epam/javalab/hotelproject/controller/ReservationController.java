package com.epam.javalab.hotelproject.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;

@WebServlet(
        name = "ReservationServlet",
        urlPatterns = { "/order"}
)
public class ReservationController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if(session.getAttribute("login") != null){
            req.getRequestDispatcher("/jsp/order.jsp").forward(req, resp);
        } else {

            resp.sendRedirect("/login");
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/order.jsp").forward(req, resp);
        int beds = Integer.parseInt(req.getParameter("beds"));
        int stars = Integer.parseInt(req.getParameter("stars"));
        String checkIn = req.getParameter("checkIn");
        String checkOut = req.getParameter("checkOut");

        System.out.println(beds + stars + checkIn + checkOut);

    }
}
