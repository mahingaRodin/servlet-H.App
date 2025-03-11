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
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        :root {
            --primary-color: #4361ee;
            --primary-hover: #3a56d4;
            --secondary-color: #f8f9fa;
            --text-color: #333;
            --border-color: #e0e0e0;
            --success-color: #4CAF50;
            --error-color: #f44336;
        }

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: var(--secondary-color);
            color: var(--text-color);
            line-height: 1.6;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            padding: 20px;
        }

        .container {
            width: 100%;
            max-width: 550px;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
            overflow: hidden;
        }

        .header {
            background-color: var(--primary-color);
            color: white;
            padding: 20px;
            text-align: center;
            position: relative;
        }

        .header h2 {
            margin: 0;
            font-weight: 600;
        }

        .back-btn {
            position: absolute;
            left: 20px;
            top: 50%;
            transform: translateY(-50%);
            color: white;
            text-decoration: none;
            display: flex;
            align-items: center;
            gap: 5px;
            font-size: 0.9rem;
        }

        .content {
            padding: 30px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: 500;
            color: #555;
        }

        input, select {
            width: 100%;
            padding: 12px;
            border: 1px solid var(--border-color);
            border-radius: 4px;
            font-size: 16px;
            transition: border-color 0.3s;
        }

        input:focus, select:focus {
            outline: none;
            border-color: var(--primary-color);
            box-shadow: 0 0 0 2px rgba(67, 97, 238, 0.2);
        }

        .btn {
            display: inline-block;
            width: 100%;
            background-color: var(--primary-color);
            color: white;
            padding: 14px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            font-weight: 500;
            transition: background-color 0.3s;
            text-align: center;
        }

        .btn:hover {
            background-color: var(--primary-hover);
        }

        .error-message {
            color: var(--error-color);
            margin-top: 15px;
            text-align: center;
            font-size: 0.9rem;
        }

        .footer {
            padding: 20px;
            border-top: 1px solid var(--border-color);
            text-align: center;
        }

        .footer a {
            color: var(--primary-color);
            text-decoration: none;
            font-weight: 500;
            display: inline-flex;
            align-items: center;
            gap: 5px;
        }

        .footer a:hover {
            text-decoration: underline;
        }

        @media (max-width: 600px) {
            .container {
                border-radius: 0;
            }

        {
            .container {
                border-radius: 0;
            }

            .content {
                padding: 20px;
            }

            .back-btn {
                font-size: 0.8rem;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <a href="<%= request.getContextPath() %>/customer/list" class="back-btn">
            <i class="fas fa-arrow-left"></i> Back
        </a>
        <h2>Edit Customer</h2>
    </div>

    <div class="content">
        <!-- Form to edit customer details -->
        <form action="<%= request.getContextPath() %>/customer/update" method="POST">
            <!-- Hidden field to store the customer ID -->
            <input type="hidden" name="id" value="<%= customer.getUserId() %>">

            <div class="form-group">
                <label for="fullName">Full Name</label>
                <input type="text" id="fullName" name="fullName" value="<%= customer.getFullName() %>" required>
            </div>

            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" value="<%= customer.getEmail() %>" required>
            </div>

            <div class="form-group">
                <label for="phoneNumber">Phone Number</label>
                <input type="tel" id="phoneNumber" name="phoneNumber" value="<%= customer.getPhoneNumber() %>" required>
            </div>

            <div class="form-group">
                <label for="role">Role</label>
                <select id="role" name="role" required>
                    <option value="customer" <%= "customer".equals(customer.getRole()) ? "selected" : "" %>>Customer</option>
                    <option value="admin" <%= "admin".equals(customer.getRole()) ? "selected" : "" %>>Admin</option>
                </select>
            </div>

            <button type="submit" class="btn">Update Customer</button>
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
    </div>

    <div class="footer">
        <a href="<%= request.getContextPath() %>/customer/list">
            <i class="fas fa-list"></i> Back to Customer List
        </a>
    </div>
</div>
</body>
</html>