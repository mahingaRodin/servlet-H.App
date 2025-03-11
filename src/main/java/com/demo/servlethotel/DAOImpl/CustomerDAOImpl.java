package com.demo.servlethotel.DAOImpl;

import com.demo.servlethotel.DAO.CustomerDAO;
import com.demo.servlethotel.models.Customer;
import com.demo.servlethotel.models.User;
import com.demo.servlethotel.utility.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    Connection conn = DBConnection.getConnection();

    public CustomerDAOImpl() throws SQLException {
    }

    @Override
    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO users (full_name, email, password_hash, phone_number, role) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, customer.getFullName());         // full_name
            preparedStatement.setString(2, customer.getEmail());            // email
            preparedStatement.setString(3, customer.getPasswordHash());     // password_hash
            preparedStatement.setString(4, customer.getPhoneNumber());      // phone_number
            preparedStatement.setString(5, "customer");                     // role set to 'customer'

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new customer was inserted successfully!");
            } else {
                System.out.println("User already exists!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE role = 'customer'";

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                // Map each row of the result to a Customer object
                Customer customer = new Customer(
                        resultSet.getInt("user_id"),
                        resultSet.getString("full_name"),
                        resultSet.getString("email"),
                        resultSet.getString("password_hash"),
                        resultSet.getString("phone_number"),
                        resultSet.getString("role"),
                        resultSet.getTimestamp("created_at")
                );
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }


    @Override
    public Customer getCustomerById(int id) {
        String sql = "SELECT * FROM users WHERE user_id = ? AND role = 'customer'";
        Customer customer = null;

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    customer = new Customer(
                            resultSet.getInt("user_id"),
                            resultSet.getString("full_name"),
                            resultSet.getString("email"),
                            resultSet.getString("password_hash"),
                            resultSet.getString("phone_number"),
                            resultSet.getString("role"),
                            resultSet.getTimestamp("created_at")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }


    @Override
    public boolean updateCustomer(Customer customer) {
        String sql = "UPDATE users SET full_name = ?, email = ?, password_hash = ?, phone_number = ? WHERE user_id = ? AND role = 'customer'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            System.out.println("Updating customer: " + customer.toString());
            preparedStatement.setString(1, customer.getFullName() != null ? customer.getFullName() : "");
            preparedStatement.setString(2, customer.getEmail() != null ? customer.getEmail() : "");
            preparedStatement.setString(3, customer.getPasswordHash() != null ? customer.getPasswordHash() : "");
            preparedStatement.setString(4, customer.getPhoneNumber() != null ? customer.getPhoneNumber() : "");
            preparedStatement.setInt(5, customer.getUserId());
            System.out.println("Executing SQL: " + preparedStatement.toString());
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Customer details updated successfully! Rows affected: " + rowsUpdated);
                return true;
            } else {
                System.out.println("Failed to update customer: No rows affected. Check user_id=" + customer.getUserId() + " and role='customer'");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void deleteCustomer(int id) {
        String sql = "DELETE FROM users WHERE user_id = ? AND role = 'customer'";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Customer deleted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
