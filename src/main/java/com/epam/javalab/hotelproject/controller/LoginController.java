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

/**
 * An HTTP servlet that is suitable for login processing. If user's login and password
 * are stored in data base he will be redirected to home page where they are able to book a room.
 *
 * @author Denis Iaichnikov, Andrey Kirshin
 * @version 1.0
 */

@WebServlet(
        name = "UserServlet",
        urlPatterns = {"/login"}
)
public class LoginController extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("password");
        User user = new User("", "", login, pass);
        String message = null;

        if (userService.authorize(user)) {
            HttpSession session = req.getSession();
            session.setAttribute("login", login);
            resp.sendRedirect("/");
        } else {
            message = "Password or login is wrong";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
        }
    }
}
