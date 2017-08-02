package com.epam.javalab.hotelproject.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "RegistrationServlet",
        urlPatterns = {"/registration"}
)
public class RegistrationController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/registration.jsp").forward(req, resp);

        String userName = req.getParameter("userName");
        String email = req.getParameter("email");
        String login = req.getParameter("login");
        String pass = req.getParameter("password");

        System.out.println(userName);
        System.out.println(email);
        System.out.println(login);
        System.out.println(pass);
    }
}
