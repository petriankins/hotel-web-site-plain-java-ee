package com.epam.javalab.hotelproject.repository;

import com.epam.javalab.hotelproject.model.Bill;
import com.epam.javalab.hotelproject.model.Request;

import java.util.List;

/**
 * @author Sergei Petriankin <Sergei_Petriankin@epam.com>
 * @author Denis Iaichnikov <Denis_Iaichnikov@epam.com>
 * @author Andrei Kirshin <Andrei_Kirshin@epam.com>
 * @author Maksim Starshinov <Maksim_Starshinov@epam.com>
 * @since 1.0
 */
public interface BillDAO {
    /**
     * Retrieves a <code>{@link List}</code> of all bills, wraps each bill in {@link Bill}
     *
     * @return <code>List</code> with all existing bills
     */
    List<Bill> findAll();

    /**
     * Saves {@link Bill} in data source
     *
     * @param bill {@link Bill} bean to save
     * @return <code>true</code> if bill was saved, otherwise false
     */
    boolean insertBill(Bill bill);

    /**
     * Updates {@link Bill} in data source
     *
     * @param bill {@link Bill} bean to update
     * @return <code>true</code> if bill was updated, otherwise false
     */
    boolean updateBill(Bill bill);

    /**
     * Deletes {@link Bill} in data source
     *
     * @param bill {@link Bill} to delete
     * @return <code>true</code> if bill was deleted, otherwise false
     */
    boolean deleteBill(Bill bill);

    /**
     * Tries to find id of a {@link Bill} in data source based on provided {@link Request}
     *
     * @param request {@link Request} for search criteria
     * @return {@link Bill} id or <code>0</code> if nothing was found
     */
    int getBillId(Request request);

    /**
     * Tries to find a {@link Bill} in data source based on provided id
     *
     * @param id {@link Bill} id for search criteria
     * @return {@link Bill} as result, if no bill was found will return bean with default value in fields
     */
    Bill findById(int id);
}
