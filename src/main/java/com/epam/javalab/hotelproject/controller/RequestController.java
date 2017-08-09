package com.epam.javalab.hotelproject.controller;

import com.epam.javalab.hotelproject.model.Request;
import com.epam.javalab.hotelproject.service.RequestService;
import com.epam.javalab.hotelproject.service.RequestServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "RequestController",
        urlPatterns = "/request"
)
public class RequestController extends HttpServlet {
    private final static Logger         LOGGER         = Logger.getLogger(RequestController.class);
    private final        RequestService requestService = new RequestServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestNum = req.getParameter("num").trim();
        Request request;
        if (requestNum != null && !requestNum.isEmpty()) {
            request = requestService.findByNumber(Integer.parseInt(requestNum));
            req.setAttribute("request", request);
        } else {
            resp.sendRedirect("/");
        }
        req.getRequestDispatcher("/jsp/request/view.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
