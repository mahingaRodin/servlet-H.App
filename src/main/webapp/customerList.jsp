<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer List</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 8px;
            text-align: left;
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
        <td><%= customer.getId() %></td>
        <td><%= customer.getFullName() %></td>
        <td><%= customer.getEmail() %></td>
        <td><%= customer.getPhoneNumber() %></td>
        <td><%= customer.getRole() %></td>
        <td>
            <!-- Action buttons to edit or delete a customer -->
            <a href="<%= request.getContextPath() %>/customer/edit?id=<%= customer.getId() %>">Edit</a> |
            <a href="<%= request.getContextPath() %>/customer/delete?id=<%= customer.getId() %>">Delete</a>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="6" style="text-align: center;">No customers found</td>
    </tr>
    <% } %>
    </tbody>
</table>

<!-- Link to add a new customer -->
<br>
<a href="<%= request.getContextPath() %>/customer/add">Add New Customer</a>
</body>
</html>
