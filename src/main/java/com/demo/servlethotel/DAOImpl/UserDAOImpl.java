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
        if (user.getFullName() == null || user.getFullName().isEmpty()) {
            throw new IllegalArgumentException("❌ Full name cannot be null or empty!");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("❌ Email cannot be null or empty!");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("❌ Password hash cannot be null or empty!");
        }

        String email = user.getEmail();
        String fullName = user.getFullName();

        // Check if the email already exists
        try (Connection conn = DBConnection.getConnection()) {
            String checkEmailQuery = "SELECT * FROM users WHERE email = ?";
            try (PreparedStatement checkEmailStmt = conn.prepareStatement(checkEmailQuery)) {
                checkEmailStmt.setString(1, email);
                ResultSet resultSet = checkEmailStmt.executeQuery();

                if (resultSet.next()) {
                    throw new IllegalArgumentException("❌ Email already exists!");
                }
            }

            // Proceed with inserting the user if the email is unique
            String insertQuery = "INSERT INTO users (full_name, email, password_hash, role) VALUES (?, ?, ?, ?)";
            try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                insertStmt.setString(1, fullName);  // Set full_name
                insertStmt.setString(2, email);     // Set email
                insertStmt.setString(3, user.getPassword()); // Set password_hash
                insertStmt.setString(4, "admin");   // Set role to "admin"

                // Execute the insert query
                int rowsInserted = insertStmt.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("✅ Admin user added successfully!");
                } else {
                    throw new RuntimeException("❌ Failed to add admin: No rows inserted.");
                }
            }
        } catch (SQLException e) {
            // Log the SQL exception and rethrow as a runtime exception
            System.err.println("❌ SQL Error: " + e.getMessage());
            throw new RuntimeException("Failed to add admin: " + e.getMessage(), e);
        }
    }

    @Override
    public User getAdminByUsernameAndPassword(String fullName, String password) {
        String sql = "SELECT * FROM users WHERE full_name = ? AND role = 'admin'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Log the query and parameters for debugging
            System.out.println("Executing Query: " + sql);
            System.out.println("Parameters: fullName=" + fullName);

            stmt.setString(1, fullName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Log the user details for debugging
                int id = rs.getInt("user_id");
                String dbFullName = rs.getString("full_name");
                String email = rs.getString("email");
                String passwordHash = rs.getString("password_hash");
                String role = rs.getString("role");

                System.out.println("User found in database: " + dbFullName);
                System.out.println("Hashed Password from DB: " + passwordHash);

                // Verify the password
                if (PasswordUtil.checkPassword(password, passwordHash)) {
                    return new User(id, dbFullName, passwordHash, email, role);
                } else {
                    System.out.println("Password verification failed");
                }
            } else {
                System.out.println("User not found in the database");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}