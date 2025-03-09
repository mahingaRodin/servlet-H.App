package com.demo.servlethotel.serviceImpl;

import com.demo.servlethotel.DAO.CustomerDAO;
import com.demo.servlethotel.DAOImpl.CustomerDAOImpl;
import com.demo.servlethotel.models.Customer;
import com.demo.servlethotel.services.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private CustomerDAO customerDAO;

    public CustomerServiceImpl() {
        this.customerDAO = new CustomerDAOImpl();
    }

    @Override
    public void addCustomer(Customer customer) {
        customerDAO.addCustomer(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDAO.getCustomers();
    }

    @Override
    public Customer getCustomerById(int id) {
        return customerDAO.getCustomerById(id);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerDAO.updateCustomer(customer);
    }

    @Override
    public void deleteCustomer(int id) {
        customerDAO.deleteCustomer(id);
    }
}
