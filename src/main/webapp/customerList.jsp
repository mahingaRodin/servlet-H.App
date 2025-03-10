<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.demo.servlethotel.models.Customer" %>
<%@ page import="com.demo.servlethotel.models.User" %>
<!-- Replace with your actual Customer class package -->

<%
    // Check if the user is logged in, if not redirect to the login page
    User loggedInUser = (User) session.getAttribute("loggedInUser");
    if (loggedInUser == null) {
        response.sendRedirect(request.getContextPath() + "/admin/login");
        return;
    }
%>
<html>
<head>
    <title>Customer List</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #f5f5f5;
        }
        .no-data {
            text-align: center;
            font-style: italic;
            color: #888;
        }
        .actions a {
            margin-right: 5px;
            text-decoration: none;
            color: #007bff;
        }
        .actions a:hover {
            text-decoration: underline;
        }
        .logout-link {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 15px;
            background-color: #dc3545;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }
        .logout-link:hover {
            background-color: #c82333;
        }
    </style>
</head>
<body>
<h2>Customer List</h2>

<!-- Table to display customers -->
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Full Name</th>
        <th>Email</th>
        <th>Phone Number</th>
        <th>Role</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <%
        // Get the list of customers from the request
        List<Customer> customers = (List<Customer>) request.getAttribute("customers");
        if (customers != null && !customers.isEmpty()) {
            for (Customer customer : customers) {
    %>
    <tr>
        <td><%= customer.getUserId() %></td>
        <td><%= customer.getFullName() %></td>
        <td><%= customer.getEmail() %></td>
        <td><%= customer.getPhoneNumber() %></td>
        <td><%= customer.getRole() %></td>
        <td class="actions">
            <!-- Action buttons to edit or delete a customer -->
            <a href="<%= request.getContextPath() %>/customer/edit?id=<%= customer.getUserId() %>">Edit</a>
            <a href="<%= request.getContextPath() %>/customer/delete?id=<%= customer.getUserId() %>"
               onclick="return confirm('Are you sure you want to delete this customer?');">Delete</a>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="6" class="no-data">No customers found</td>
    </tr>
    <% } %>
    </tbody>
</table>

<!-- Link to add a new customer -->
<br>
<a href="<%= request.getContextPath() %>/customer/add" style="text-decoration: none; color: #007bff;">Add New Customer</a>

<!-- Logout Link -->
<br>
<a href="<%= request.getContextPath() %>/customer/logout" class="logout-link">Logout</a>
</body>
</html>