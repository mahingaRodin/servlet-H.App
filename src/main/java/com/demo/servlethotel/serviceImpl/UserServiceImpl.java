package com.demo.servlethotel.serviceImpl;

import com.demo.servlethotel.DAO.UserDAO;
import com.demo.servlethotel.DAOImpl.UserDAOImpl;
import com.demo.servlethotel.models.User;
import com.demo.servlethotel.services.UserService;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO = new UserDAOImpl();

    @Override
    public void registerAdmin(User admin) {
       userDAO.addAdmin(admin);
    }

    @Override
    public User loginAdmin(String fullName, String password) {
       return userDAO.getAdminByUsernameAndPassword(fullName, password);
}
}
