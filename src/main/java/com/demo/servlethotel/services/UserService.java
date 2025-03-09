package com.demo.servlethotel.services;

import com.demo.servlethotel.models.User;

public interface UserService {
    void registerAdmin(User admin);
    User loginAdmin(String username, String password);
}
