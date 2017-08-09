package com.epam.javalab.hotelproject.controller;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.NOPLogger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@WebServlet(
        name = "LocaleController",
        urlPatterns = "/locale"
)
public class LocaleController extends HttpServlet {
    private final static Logger LOGGER = Logger.getLogger(LocaleController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String lang = req.getParameter("lang").trim();
        String url = req.getParameter("redirectTo").trim();
        LOGGER.debug("Got url: " + url);
        if (lang != null) {
            session.setAttribute("lang", lang);
        }

        resp.sendRedirect(url);
    }
}
