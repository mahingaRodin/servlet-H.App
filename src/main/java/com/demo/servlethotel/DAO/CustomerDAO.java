package com.demo.servlethotel.DAO;

import com.demo.servlethotel.models.Customer;

import java.util.List;

public interface CustomerDAO {
    void addCustomer(Customer customer);

    List<Customer> getCustomers();

    Customer getCustomerById(int id);

    boolean updateCustomer(Customer customer);

    void deleteCustomer(int id);
}
