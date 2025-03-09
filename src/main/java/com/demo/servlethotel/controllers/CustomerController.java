package com.demo.servlethotel.controllers;

import com.demo.servlethotel.models.Customer;
import com.demo.servlethotel.serviceImpl.CustomerServiceImpl;
import com.demo.servlethotel.services.CustomerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static com.demo.servlethotel.utility.PasswordUtil.hashPassword;

public class CustomerController extends HttpServlet {
    private CustomerService customerService = new CustomerServiceImpl();

    public CustomerController() throws SQLException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Customer> customers = customerService.getAllCustomers();
        request.setAttribute("customer", customers);
        request.getRequestDispatcher("/addCustomer.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phoneNumber = request.getParameter("phoneNumber");
        String passwordHash = hashPassword(password);

        Customer customer = new Customer(0, fullName, email, passwordHash, phoneNumber, "customer", null);

        customerService.addCustomer(customer);

        response.sendRedirect(request.getContextPath() + "/customer/list");
    }


}
