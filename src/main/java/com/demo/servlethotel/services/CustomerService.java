package com.demo.servlethotel.services;

import com.demo.servlethotel.models.Customer;
import java.util.List;

public interface CustomerService {

    void addCustomer(Customer customer);

    List<Customer> getAllCustomers();

    Customer getCustomerById(int id);

    boolean updateCustomer(Customer customer);

    void deleteCustomer(int id);
}
