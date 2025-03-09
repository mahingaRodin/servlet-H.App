package com.demo.servlethotel.DAO;

import com.demo.servlethotel.models.User;

public interface UserDAO {
    void addAdmin(User admin);
    User getAdminByUsernameAndPassword(String username, String password);
}
