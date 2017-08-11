package com.epam.javalab.hotelproject.service;

import com.epam.javalab.hotelproject.model.Bill;
import com.epam.javalab.hotelproject.model.Request;
import com.epam.javalab.hotelproject.model.User;
import com.epam.javalab.hotelproject.repository.RequestDAO;
import com.epam.javalab.hotelproject.repository.RequestRepository;
import org.apache.log4j.Logger;

import static com.epam.javalab.hotelproject.utils.Validator.validateRequestBean;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Provides API for working with requests.
 *
 * @author Iaichnikov Denis,
 * @version 1.0
 * @since 1.0*/
public class RequestServiceImpl implements RequestService {
    private final static Logger      LOGGER      = Logger.getLogger(RequestServiceImpl.class);
    private final        RequestDAO  requestDAO  = new RequestRepository();
    private final        BillService billService = new BillServiceImpl();

    @Override
    public List<Request> findAll() {
        return requestDAO.findAll();
    }

    @Override
    public boolean saveRequest(Request request) {
        request.setNumber(generateRequestNumber());
        return validateRequestBean(request) && createRequest(request);
    }

    @Override
    public boolean deleteRequest(Request request) {
        return requestDAO.deleteRequest(request);
    }

    @Override
    public Request findByNumber(int number) {
        return requestDAO.findByNumber(number);
    }

    @Override
    public List<Request> findByUser(User user) {
        return requestDAO.findByUser(user);
    }

    private boolean createRequest(Request request) {

        return requestDAO.insertRequest(request);
    }

    @Override
    public List<Request> findAllUnhandledRequests() {
        return requestDAO.findAllUnhandledRequests();
    }

    private static int generateRequestNumber() {
        AtomicInteger atomicInteger;
        atomicInteger = new AtomicInteger(RequestRepository.returnMaxRequestNumber());
        return incrementRequestNumber(atomicInteger);
    }

    @Override
    public boolean updateRequest(Request request) {
        return requestDAO.updateRequest(request);
    }

    @Override
    public String getRequestStatus(Request request) {
        if (!validateRequestBean(request)) {
            LOGGER.debug("Validation failed for request bean!");
            return "";
        }
        Bill requestBill = billService.getRequestBill(request);
        if (requestBill.getId() == 0) {
            LOGGER.debug("Didn't find any bill for Request #" + request.getNumber());
            return "1";
        }
        if (requestBill.getSum() == requestBill.getPaid()) {
            LOGGER.debug("Bill for Request #" + request.getNumber() + " is fully paid");
            return "3";
        }
        LOGGER.debug("Bill for Request #" + request.getNumber() + " awaits payment");
        return "2";
    }

    private static int incrementRequestNumber(AtomicInteger number) {
        return number.incrementAndGet();
    }
}

