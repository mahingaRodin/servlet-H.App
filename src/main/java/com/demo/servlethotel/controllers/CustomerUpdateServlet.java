package com.demo.servlethotel.controllers;

import com.demo.servlethotel.models.Customer;
import com.demo.servlethotel.serviceImpl.CustomerServiceImpl;
import com.demo.servlethotel.services.CustomerService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

public class CustomerUpdateServlet extends HttpServlet {
    private CustomerService customerService;  // Use CustomerService

    @Override
    public void init() throws ServletException {
        try {
            customerService = new CustomerServiceImpl();  // Initialize the CustomerService
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Do Get for Displaying Customer Details
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            deleteCustomer(request, response);
        } else if ("edit".equals(action)) {
            editCustomer(request, response);
        } else {
            listCustomers(request, response);
        }
    }

    // List all customers
    private void listCustomers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("customers", customerService.getAllCustomers());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/customer/list");  // JSP page for customer list
        dispatcher.forward(request, response);
    }

    // Delete a customer
    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("id"));
        customerService.deleteCustomer(customerId);
        response.sendRedirect(request.getContextPath() + "/customer/list");  // Redirect to customer list after deletion
    }

    // Edit a customer
    private void editCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("id"));
        Customer customer = customerService.getCustomerById(customerId);
        request.setAttribute("customer", customer);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/customer/edit.jsp");  // JSP page for editing customer details
        dispatcher.forward(request, response);
    }

    // Do Post for handling customer update form submission
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("update".equals(action)) {
            updateCustomer(request, response);
        }
    }

    // Update customer information
    private void updateCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String role = request.getParameter("role");

        Customer customer = new Customer(id, fullName, email, phoneNumber, role);

        customerService.updateCustomer(customer);  // Call the update method from CustomerService

        // If it's an AJAX request, respond with success; otherwise, redirect to the customer list
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            response.setStatus(HttpServletResponse.SC_OK);  // Respond with success for AJAX requests
        } else {
            response.sendRedirect(request.getContextPath() + "/customer/list");  // Redirect to customer list after update
        }
    }
}
