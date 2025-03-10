<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.demo.servlethotel.models.Customer" %>
<%@ page import="com.demo.servlethotel.models.User" %>

<%
    // Check if the user is logged in, if not redirect to the login page
    User loggedInUser = (User) session.getAttribute("loggedInUser");
    if (loggedInUser == null) {
        response.sendRedirect(request.getContextPath() + "/admin/login");
        return;
    }

    // Retrieve the customer to be edited from the request
    Customer customer = (Customer) request.getAttribute("customer");
    if (customer == null) {
        response.sendRedirect(request.getContextPath() + "/customer/list");
        return;
    }
%>
<html>
<head>
    <title>Edit Customer</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        h2 {
            color: #333;
        }
        form {
            max-width: 400px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #f9f9f9;
        }
        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }
        input[type="text"], input[type="email"], input[type="tel"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
        .error-message {
            color: red;
            margin-top: 10px;
        }
        .back-link {
            display: block;
            margin-top: 20px;
            text-align: center;
            color: #007bff;
            text-decoration: none;
        }
        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<h2>Edit Customer</h2>

<!-- Form to edit customer details -->
<form action="<%= request.getContextPath() %>/customer/update" method="POST">
    <!-- Hidden field to store the customer ID -->
    <input type="hidden" name="id" value="<%= customer.getUserId() %>">

    <label for="fullName">Full Name:</label>
    <input type="text" id="fullName" name="fullName" value="<%= customer.getFullName() %>" required>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" value="<%= customer.getEmail() %>" required>

    <label for="phoneNumber">Phone Number:</label>
    <input type="tel" id="phoneNumber" name="phoneNumber" value="<%= customer.getPhoneNumber() %>" required>

    <label for="role">Role:</label>
    <input type="text" id="role" name="role" value="<%= customer.getRole() %>" required>

    <input type="submit" value="Update Customer">
</form>

<!-- Display error message if there is one -->
<%
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null) {
%>
<p class="error-message"><%= errorMessage %></p>
<%
    }
%>

<!-- Link to go back to the customer list -->
<a href="<%= request.getContextPath() %>/customer/list" class="back-link">Back to Customer List</a>
</body>
</html>