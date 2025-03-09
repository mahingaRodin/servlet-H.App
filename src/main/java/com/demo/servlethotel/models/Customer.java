package com.demo.servlethotel.models;

import java.sql.Timestamp;

public class Customer {
    private int userId;
    private String fullName;
    private String email;
    private String passwordHash;
    private String phoneNumber;
    private String role;
    private Timestamp createdAt;

    // 🔹 Constructor
    public Customer(int userId, String fullName, String email, String passwordHash, String phoneNumber, String role, Timestamp createdAt) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.createdAt = createdAt;
    }

    // 🔹 Default Constructor
    public Customer() {}

    public Customer(int id, String fullName, String email, String phoneNumber, String role) {
    }

    // 🔹 Getters & Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "userId=" + userId +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role='" + role + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
