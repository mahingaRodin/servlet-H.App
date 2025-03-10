package com.demo.servlethotel.controllers;

import com.demo.servlethotel.models.User;
import com.demo.servlethotel.serviceImpl.UserServiceImpl;
import com.demo.servlethotel.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AdminLoginServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to the admin login page
        request.getRequestDispatcher("/adminLogin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve form parameters
        String fullName = request.getParameter("fullName");
        String password = request.getParameter("password");

        // Log the input parameters for debugging
        System.out.println("Entered Full Name: " + fullName);
        System.out.println("Entered Password: " + password);

        // Validate input parameters
        if (fullName == null || fullName.isEmpty() || password == null || password.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/admin/login?error=All fields are required");
            return;
        }

        // Attempt to log in the admin user
        User user = userService.loginAdmin(fullName, password);

        if (user != null) {
            // Set a session attribute or JWT token to track logged-in user
            request.getSession().setAttribute("loggedInUser", user);
            response.sendRedirect(request.getContextPath() + "/customer/list"); // Redirect to admin dashboard
        } else {
            response.sendRedirect(request.getContextPath() + "/admin/login?error=Invalid credentials");
        }

    }
}