package com.epam.javalab.hotelproject.controller;

import com.epam.javalab.hotelproject.model.Bill;
import com.epam.javalab.hotelproject.model.Request;
import com.epam.javalab.hotelproject.model.Room;
import com.epam.javalab.hotelproject.model.User;
import com.epam.javalab.hotelproject.service.*;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(
        name = "billServlet",
        urlPatterns = {"/bill"}
)
public class BillController extends HttpServlet {
    private final static Logger            LOGGER            = Logger.getLogger(BillController.class);
    private final        RequestService    requestService    = new RequestServiceImpl();
    private final        RoomService       roomService       = new RoomServiceImpl();
    private final        BillService       billService       = new BillServiceImpl();
    private final        UserService       userService       = new UserServiceImpl();
    private final        RoomStatusService roomStatusService = new RoomStatusServiceImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestNumber = req.getParameter("request").trim();
        Request userRequest = requestService.findByNumber(Integer.parseInt(requestNumber));
        Bill bill = billService.getRequestBill(userRequest);

        req.setAttribute("request", userRequest);
        req.setAttribute("bill", bill);

        req.getRequestDispatcher("/jsp/bill.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String roomNumber = req.getParameter("roomNumber");
        Room room = roomService.findByNumber(Integer.parseInt(roomNumber));
        req.setAttribute("room", room);

        HttpSession session = req.getSession();
        Request request = (Request) session.getAttribute("request");

        System.out.println("request # " + request.getNumber());
        System.out.println(roomNumber);

        Bill bill = billService.createBill(request, room);
        if (bill != null) {
            roomStatusService.bookRoom(request, room);
        }
        System.out.println("Request ID: " + billService.getBillId(request));
        req.setAttribute("bill", bill);
        req.getRequestDispatcher("/jsp/bill.jsp").forward(req, resp);
    }
}
