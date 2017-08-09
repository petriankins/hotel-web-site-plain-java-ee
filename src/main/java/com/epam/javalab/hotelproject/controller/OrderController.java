package com.epam.javalab.hotelproject.controller;

import com.epam.javalab.hotelproject.model.Request;
import com.epam.javalab.hotelproject.model.User;
import com.epam.javalab.hotelproject.service.RequestService;
import com.epam.javalab.hotelproject.service.RequestServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(
        name = "ReservationServlet",
        urlPatterns = {"/order"}
)
public class OrderController extends HttpServlet {
    private final RequestService requestService = new RequestServiceImpl();

    /*@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (session.getAttribute("user") != null) {
            req.getRequestDispatcher("/jsp/order.jsp").forward(req, resp);
        } else {

            resp.sendRedirect("/login");
        }
    }*/

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println("User id: " + user.getId());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int beds = 0;
        int stars = 0;
        Date defaultDate = new Date();
        Date dateFrom = defaultDate;
        Date dateTo = defaultDate;

        try {
            beds = Integer.parseInt(req.getParameter("beds").trim());
            stars = Integer.parseInt(req.getParameter("stars").trim());
            dateFrom = dateFormat.parse(req.getParameter("checkIn").trim());
            dateTo = dateFormat.parse(req.getParameter("checkOut").trim());
            System.out.println("From: " + dateFrom);
            System.out.println("To: " + dateTo);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Request request = new Request(user.getId(), beds, stars, dateFrom, dateTo, "");

        String message = null;

        if (request.getBeds() == 0 || request.getClassID() == 0 || request.getDateFrom().equals(defaultDate) ||
            request.getDateTo().equals(defaultDate)) {
            message = "Fill all fields please";
            req.setAttribute("message", message);
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } else {
            if (requestService.saveRequest(request)) {
                message = "Reservation have gone correct. Bill will be email to you";
            } else {
                message = "Something went wrong : (";
            }
            req.setAttribute("message", message);
            req.getRequestDispatcher("index.jsp").forward(req, resp);

        }
    }
}
