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
    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        try {
            // Initialize the CustomerService
            customerService = new CustomerServiceImpl();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize CustomerService", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle GET requests (display the edit form)
        try {
            // Retrieve the customer ID from the request
            int customerId = Integer.parseInt(request.getParameter("id"));

            // Fetch the customer details from the database
            Customer customer = customerService.getCustomerById(customerId);

            if (customer != null) {
                // Set the customer in the request attribute
                request.setAttribute("customer", customer);

                // Forward to the edit page (customerEdit.jsp)
                RequestDispatcher dispatcher = request.getRequestDispatcher("/customerEdit.jsp");
                dispatcher.forward(request, response);
            } else {
                // Customer not found: redirect to customer list
                response.sendRedirect(request.getContextPath() + "/customer/list");
            }
        } catch (NumberFormatException e) {
            // Invalid customer ID: redirect to customer list
            response.sendRedirect(request.getContextPath() + "/customer/list");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve form data
            String idParam = request.getParameter("id");
            String fullName = request.getParameter("fullName");
            String email = request.getParameter("email");
            String phoneNumber = request.getParameter("phoneNumber");
            String role = request.getParameter("role");

            // Log the received parameters
            System.out.println("Received parameters: id=" + idParam + ", fullName=" + fullName +
                    ", email=" + email + ", phoneNumber=" + phoneNumber + ", role=" + role);

            int id = (idParam != null && !idParam.isEmpty()) ? Integer.parseInt(idParam) : 0;
            Customer customer = new Customer(id, fullName, email, phoneNumber, role);

            // Log the Customer object
            System.out.println("Customer object: " + customer.toString());

            boolean success = customerService.updateCustomer(customer);

            if (success) {
                response.sendRedirect(request.getContextPath() + "/customer/list");
            } else {
                request.setAttribute("errorMessage", "Failed to update customer. Please try again.");
                doGet(request, response);
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid ID format: " + request.getParameter("id"));
            response.sendRedirect(request.getContextPath() + "/customer/list");
        }
    }
}