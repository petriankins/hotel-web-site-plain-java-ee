package com.epam.javalab.hotelproject.controller;

import com.epam.javalab.hotelproject.model.User;
import com.epam.javalab.hotelproject.service.UserService;
import com.epam.javalab.hotelproject.service.UserServiceImpl;
import org.apache.log4j.Logger;
import sun.rmi.runtime.Log;

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
    private static final Logger      LOGGER      = Logger.getLogger(LoginController.class);
    private final        UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login").trim();
        String pass = req.getParameter("password").trim();
        User user = new User(login, pass);

        if (userService.authenticate(user)) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            LOGGER.info("Successful authentication for user " + user.getLogin());

        } else {
            LOGGER.info("Error in login or password. Login: " + user.getLogin() + ";Password: " + user.getPassword());

            req.setAttribute("message", "1");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }

        resp.sendRedirect("/");
    }
}
