package com.demo.servlethotel.controllers;

import com.demo.servlethotel.models.Customer;
import com.demo.servlethotel.serviceImpl.CustomerServiceImpl;
import com.demo.servlethotel.services.CustomerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class CustomerListServlet extends HttpServlet {
    private CustomerService customerService = new CustomerServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Customer> customers = customerService.getAllCustomers();

        request.setAttribute("customers", customers);

        request.getRequestDispatcher("/customerList.jsp").forward(request, response);
    }
}
