package com.epam.javalab.hotelproject.controller;

import com.epam.javalab.hotelproject.model.Request;
import com.epam.javalab.hotelproject.service.RequestService;
import com.epam.javalab.hotelproject.service.RequestServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

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
    private RequestService requestService = new RequestServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();


        if (session.getAttribute("user") != null) {
            List<Request> allRequests;
            allRequests = requestService.findAll();
            req.setAttribute("allRequests", allRequests);

            List<Request> unHendls = requestService.findAllUnhandledRequests();
            req.setAttribute("unHendls", unHendls);

            req.getRequestDispatcher("/jsp/administrator.jsp").forward(req, resp);

        } else {
            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enumeration<String> en = req.getParameterNames();

        while (en.hasMoreElements()){
            System.out.println(en.nextElement());
        }
        resp.sendRedirect("/appointRoom");

    }
}
