package com.epam.javalab.hotelproject.controller;

import com.epam.javalab.hotelproject.model.Request;
import com.epam.javalab.hotelproject.model.User;
import com.epam.javalab.hotelproject.service.RequestService;
import com.epam.javalab.hotelproject.service.RequestServiceImpl;
import com.epam.javalab.hotelproject.service.SecurityService;
import com.epam.javalab.hotelproject.service.SecurityServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//TODO what if there is no request with such number?
@WebServlet(
        name = "RequestController",
        urlPatterns = "/request"
)
public class RequestController extends HttpServlet {
    private final static Logger          LOGGER          = Logger.getLogger(RequestController.class);
    private final        RequestService  requestService  = new RequestServiceImpl();
    private final        SecurityService securityService = new SecurityServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action").trim();

        switch (action) {
            case "edit": {
                editRequest(req, resp);
                break;
            }
            case "view": {
                viewRequest(req, resp);
                break;
            }
            default:
                resp.sendRedirect("/");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/");
    }

    private void viewRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestNumber = getRequestParameter(req, "num");
        Request userRequest = requestService.findByNumber(Integer.parseInt(requestNumber));
        User user = (User) req.getSession().getAttribute("user");

        if (securityService.authorize(user, userRequest)) {
            req.setAttribute("request", userRequest);

            req.getRequestDispatcher("/jsp/request/view.jsp").forward(req, resp);
        }

        resp.sendRedirect("/");
    }

    private void editRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/request/edit.jsp").forward(req, resp);
    }

    private String getRequestParameter(HttpServletRequest req, String parameterName) {
        String param = req.getParameter(parameterName);

        if (param != null && !param.isEmpty()) {
            param.trim();
        } else {
            param = "";
        }

        return param;
    }
}
