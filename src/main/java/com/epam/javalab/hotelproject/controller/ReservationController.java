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

        if(session.getAttribute("user") != null){
            req.getRequestDispatcher("/jsp/order.jsp").forward(req, resp);
        } else {

            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String beds = req.getParameter("beds");
        String stars = req.getParameter("stars");
        String checkIn = req.getParameter("checkIn");
        String checkOut = req.getParameter("checkOut");

        String message = null;

        if(beds == "" || stars == "" || checkIn == "" || checkOut == ""){
            message = "Fill all fields please";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/order.jsp").forward(req, resp);
        }
        else {
            message = "Reservation have gone correct. Bill will be email to you";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/order.jsp").forward(req, resp);
            System.out.println(beds + stars + checkIn + checkOut);
        }
    }
}
