package com.epam.javalab.hotelproject.controller;

import com.epam.javalab.hotelproject.model.Request;
import com.epam.javalab.hotelproject.model.User;
import com.epam.javalab.hotelproject.service.*;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//TODO what if there is no request with such number?
//TODO need method for request status. is there a bill for it or no
@WebServlet(
        name = "RequestController",
        urlPatterns = "/request"
)
public class RequestController extends HttpServlet {
    private final static Logger          LOGGER          = Logger.getLogger(RequestController.class);
    private final        RequestService  requestService  = new RequestServiceImpl();
    private final        SecurityService securityService = new SecurityServiceImpl();
    private final        BillService     billService     = new BillServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action").trim();

        switch (action) {
            case "view": {
                viewRequest(req, resp);
                break;
            }
            case "edit": {
                editRequest(req, resp);
                break;
            }
            case "delete": {
                deleteRequest(req, resp);
                break;
            }
            default:
                resp.sendRedirect("/");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        LOGGER.info("Trying to update request!");
        Request userRequest = extractUserRequestFromHttpRequest(req);
        LOGGER.info("Request number: " + userRequest.getNumber());
        if (requestService.getRequestStatus(userRequest).equals("1") && securityService.authorize(user, userRequest)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            int beds = 0;
            int stars = 0;
            Date defaultDate = new Date();
            Date dateFrom = defaultDate;
            Date dateTo = defaultDate;

            try {
                userRequest.setBeds(Integer.parseInt(getRequestParameter(req, "beds")));
                userRequest.setClassID(Integer.parseInt(getRequestParameter(req, "stars")));
                userRequest.setDateFrom(dateFormat.parse(getRequestParameter(req, "checkIn")));
                userRequest.setDateTo(dateFormat.parse(getRequestParameter(req, "checkOut")));
                userRequest.setComments(getRequestParameter(req, "comments"));
                requestService.updateRequest(userRequest);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        resp.sendRedirect("/");
    }

    private void viewRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Request userRequest;

        if ((userRequest = extractUserRequestFromHttpRequest(req)).getId() == 0) {
            resp.sendRedirect("/");
        } else {
            req.setAttribute("request", userRequest);
            String requestStatus = requestService.getRequestStatus(userRequest);
            LOGGER.debug("Request #" + userRequest.getNumber() + " status is " + requestStatus);
            req.setAttribute("requestStatus", requestStatus);

            req.getRequestDispatcher("/jsp/request/view.jsp").forward(req, resp);
        }
    }

    private void editRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Request userRequest;

        if ((userRequest = extractUserRequestFromHttpRequest(req)).getId() == 0) {
            resp.sendRedirect("/");
        } else {
            req.setAttribute("request", userRequest);
            req.getRequestDispatcher("/jsp/request/edit.jsp").forward(req, resp);
        }
    }

    private void deleteRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = (User) req.getSession().getAttribute("user");
        Request userRequest;

        if ((userRequest = extractUserRequestFromHttpRequest(req)).getId() != 0 &&
            !requestService.getRequestStatus(userRequest).equals("3") && securityService.authorize(user, userRequest)) {
            LOGGER.debug("Trying to delete Request# " + userRequest.getNumber());
            requestService.deleteRequest(userRequest);
        }

        //TODO add message
        resp.sendRedirect("/");

    }

    private Request extractUserRequestFromHttpRequest(HttpServletRequest req) {
        String requestNumber = getRequestParameter(req, "num");
        Request userRequest = requestService.findByNumber(Integer.parseInt(requestNumber));
        User user = (User) req.getSession().getAttribute("user");

        if (!securityService.authorize(user, userRequest)) {
            userRequest = new Request();
        }

        return userRequest;
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
