package com.demo.servlethotel.controllers;

import com.demo.servlethotel.models.User;
import com.demo.servlethotel.serviceImpl.UserServiceImpl;
import com.demo.servlethotel.services.UserService;
import com.demo.servlethotel.utility.PasswordUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AdminSignupServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to the admin signup page (JSP file in WEB-INF/views)
        request.getRequestDispatcher("/WEB-INF/views/adminSignup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve form parameters
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        // Validate input parameters
        if (fullName == null || fullName.isEmpty() ||
                email == null || email.isEmpty() ||
                password == null || password.isEmpty() ||
                confirmPassword == null || confirmPassword.isEmpty()) {
            request.setAttribute("errorMessage", "All fields are required!");
            request.getRequestDispatcher("/WEB-INF/views/adminSignup.jsp").forward(request, response);
            return;
        }

        // Check if passwords match
        if (!password.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "Passwords do not match!");
            request.getRequestDispatcher("/WEB-INF/views/adminSignup.jsp").forward(request, response);
            return;
        }

        // Hash the password
        String hashedPassword = PasswordUtil.hashPassword(password);

        // Create a new User object
        User admin = new User();
        admin.setFullName(fullName);
        admin.setEmail(email);
        admin.setPassword(hashedPassword);
        admin.setRole("admin");

        try {
            // Register the admin user
            userService.registerAdmin(admin);
            response.sendRedirect(request.getContextPath() + "/admin/login?success=Admin registered successfully");
        } catch (RuntimeException e) {
            // Handle registration failure
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/adminSignup.jsp").forward(request, response);
        }
    }
}