package com.demo.servlethotel.DAOImpl;

import com.demo.servlethotel.DAO.UserDAO;
import com.demo.servlethotel.models.User;
import com.demo.servlethotel.utility.DBConnection;
import com.demo.servlethotel.utility.PasswordUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    @Override
    public void addAdmin(User user) {
        String email = user.getEmail();
        String username = user.getFullName();

        // Check if the email already exists
        try (Connection conn = DBConnection.getConnection()) {
            String checkEmailQuery = "SELECT * FROM users WHERE email = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(checkEmailQuery)) {
                preparedStatement.setString(1, email);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    // Email already exists, handle the error
                    System.out.println("Email already exists!");
                    return;
                }
            }

            // Proceed with inserting the user if the email is unique
            String insertQuery = "INSERT INTO users (username, email, password, role) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = conn.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, username);  // Set the username here
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, user.getPasswordHash()); // Ensure password is properly hashed
                preparedStatement.setString(4, "admin"); // Or user.role if needed
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get Admin by username and password for login
    @Override
    public User getAdminByUsernameAndPassword(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND role = 'admin'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (conn == null || conn.isClosed()) {
                System.out.println("❌ Failed to establish a database connection.");
                return null;
            }

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHashedPassword = rs.getString("password");

                if (PasswordUtil.checkPassword(password, storedHashedPassword)) {
                    int id = rs.getInt("id");
                    String dbUsername = rs.getString("username");
                    String email = rs.getString("email");
                    String role = rs.getString("role");

                    return new User(id, dbUsername, storedHashedPassword, email, role);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
