package com.epam.javalab.hotelproject.controller;

import com.epam.javalab.hotelproject.Main;
import com.epam.javalab.hotelproject.model.Request;
import com.epam.javalab.hotelproject.model.User;
import com.epam.javalab.hotelproject.service.RequestService;
import com.epam.javalab.hotelproject.service.RequestServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(
        name = "MainController",
        urlPatterns = ""
)
public class MainController extends HttpServlet {
    private final static Logger         LOGGER         = Logger.getLogger(MainController.class);
    private final        RequestService requestService = new RequestServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("user");

        if (currentUser != null) {
            List<Request> userRequests = requestService.findByUser(currentUser);
            req.setAttribute("userRequests", userRequests);
        }

        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
