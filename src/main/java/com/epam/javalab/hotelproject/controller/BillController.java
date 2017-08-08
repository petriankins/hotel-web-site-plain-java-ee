package com.epam.javalab.hotelproject.controller;

import com.epam.javalab.hotelproject.model.Bill;
import com.epam.javalab.hotelproject.model.Request;
import com.epam.javalab.hotelproject.model.Room;
import com.epam.javalab.hotelproject.model.User;
import com.epam.javalab.hotelproject.service.*;

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
    private RoomService roomService = new RoomServiceImpl();
    private BillService billService = new BillServiceImpl();
    private UserService userService = new UserServiceImpl();


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
        System.out.println("Total sum " + bill.getSum());
        req.setAttribute("bill", bill);
        req.getRequestDispatcher("/jsp/bill.jsp").forward(req, resp);
    }
}
