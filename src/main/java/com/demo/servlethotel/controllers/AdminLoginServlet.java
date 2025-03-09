package com.demo.servlethotel.controllers;

import com.demo.servlethotel.models.User;
import com.demo.servlethotel.serviceImpl.UserServiceImpl;
import com.demo.servlethotel.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AdminLoginServlet extends
        HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/adminLogin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.loginAdmin(username, password);

        if (user != null && user.getRole().equals("admin")) {
            request.getSession().setAttribute("loggedInUser", user);
            response.sendRedirect(request.getContextPath()+"/employee/list");  // Redirect to admin dashboard
        } else {
            response.sendRedirect(request.getContextPath()+"/admin/login?error=Invalid credentials");
        }
    }
}
