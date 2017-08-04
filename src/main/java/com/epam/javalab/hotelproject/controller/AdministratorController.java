package com.epam.javalab.hotelproject.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * An HTTP servlet that displays the administrator page content.
 *
 * @author Denis Iaichnikov, Andrey Kirshin
 * @version 1.0
 */
@WebServlet(
        name = "AdministratorServlet",
        urlPatterns = {"/administrator"}
)
public class AdministratorController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (session.getAttribute("login") != null) {
            req.getRequestDispatcher("/jsp/administrator.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
