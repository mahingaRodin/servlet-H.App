package com.demo.servlethotel.controllers;

import com.demo.servlethotel.serviceImpl.CustomerServiceImpl;
import com.demo.servlethotel.services.CustomerService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CustomerDeleteServlet extends HttpServlet {
    private CustomerService customerService;

    public CustomerDeleteServlet() throws SQLException {
        this.customerService = new CustomerServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doDelete(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");

        if (idParam != null) {
            try {
                int id = Integer.parseInt(idParam);

                customerService.deleteCustomer(id);

                response.sendRedirect(request.getContextPath() + "/customer/list");
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Invalid customer ID.");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Customer ID is required.");
        }
    }
}
