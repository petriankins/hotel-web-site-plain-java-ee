package com.epam.javalab.hotelproject.controller;

import com.epam.javalab.hotelproject.model.User;
import com.epam.javalab.hotelproject.service.UserService;
import com.epam.javalab.hotelproject.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(
        name = "RegistrationServlet",
        urlPatterns = {"/registration"}
)
public class RegistrationController extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("name");
        String lastName = req.getParameter("lastName");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User user = new User(firstName, lastName, login, password);
        String message = null;
        if (userService.registerUser(user)) {
            userService.authenticate(user);
            message = "You have been successfully registered!";
            req.setAttribute("message", message);

            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            resp.sendRedirect("/order");

        } else {
            message = "Something has gone wrong! Try again.";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/registration.jsp").forward(req, resp);
        }
    }
}
