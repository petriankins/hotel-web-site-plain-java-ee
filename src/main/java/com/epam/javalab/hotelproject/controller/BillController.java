package com.epam.javalab.hotelproject.controller;

import com.epam.javalab.hotelproject.model.Bill;
import com.epam.javalab.hotelproject.model.Request;
import com.epam.javalab.hotelproject.model.Room;
import com.epam.javalab.hotelproject.service.BillService;
import com.epam.javalab.hotelproject.service.BillServiceImpl;
import com.epam.javalab.hotelproject.service.RoomService;
import com.epam.javalab.hotelproject.service.RoomServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static javax.swing.text.html.CSS.getAttribute;

@WebServlet(
        name = "billServlet",
        urlPatterns = {"/bill"}
)
public class BillController extends HttpServlet {
    RoomService roomService = new RoomServiceImpl();
    private BillService billService = new BillServiceImpl();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String roomNumber = req.getParameter("roomNumber");

        HttpSession session = req.getSession();
        Request request = (Request) session.getAttribute("request");

        System.out.println("request # " + request.getNumber());
        System.out.println(roomNumber);

        Bill bill = billService.createBill(request);
        System.out.println("Total sum " + bill.getSum());
        req.getRequestDispatcher("/jsp/bill.jsp").forward(req, resp);
    }
}
