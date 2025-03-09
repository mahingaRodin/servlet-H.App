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
        request.getRequestDispatcher("/adminSignup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String hashedPassword = hashPassword(password);

        User admin = new User(0, username, hashedPassword, email, "admin");

        userService.registerAdmin(admin);
        response.sendRedirect(request.getContextPath()+"/admin/login");
    }

    private String hashPassword(String password) {
        return PasswordUtil.hashPassword(password);
    }
}
