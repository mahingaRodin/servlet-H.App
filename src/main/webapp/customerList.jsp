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
            --danger-color: #f44336;
            --warning-color: #ff9800;
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
            padding: 20px;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            overflow: hidden;
        }

        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 20px;
            background-color: var(--primary-color);
            color: white;
        }

        .header h2 {
            margin: 0;
            font-weight: 600;
        }

        .content {
            padding: 20px;
        }

        .table-container {
            overflow-x: auto;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
            border-radius: 4px;
            overflow: hidden;
        }

        th, td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid var(--border-color);
        }

        th {
            background-color: #f2f2f2;
            font-weight: 600;
            color: #555;
        }

        tr:hover {
            background-color: #f9f9f9;
        }

        .no-data {
            text-align: center;
            padding: 30px;
            font-style: italic;
            color: #888;
            background-color: #f9f9f9;
        }

        .actions {
            display: flex;
            gap: 10px;
        }

        .btn {
            display: inline-block;
            padding: 8px 16px;
            border-radius: 4px;
            text-decoration: none;
            font-weight: 500;
            transition: all 0.3s;
            cursor: pointer;
        }

        .btn-primary {
            background-color: var(--primary-color);
            color: white;
        }

        .btn-primary:hover {
            background-color: var(--primary-hover);
        }

        .btn-danger {
            background-color: var(--danger-color);
            color: white;
        }

        .btn-danger:hover {
            background-color: #d32f2f;
        }

        .btn-warning {
            background-color: var(--warning-color);
            color: white;
        }

        .btn-warning:hover {
            background-color: #e68a00;
        }

        .action-btn {
            padding: 6px 10px;
            border-radius: 4px;
            color: white;
            text-decoration: none;
            font-size: 0.9rem;
            display: inline-flex;
            align-items: center;
            gap: 5px;
        }

        .edit-btn {
            background-color: var(--warning-color);
        }

        .edit-btn:hover {
            background-color: #e68a00;
        }

        .delete-btn {
            background-color: var(--danger-color);
        }

        .delete-btn:hover {
            background-color: #d32f2f;
        }

        .footer {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 20px;
            border-top: 1px solid var(--border-color);
        }

        .logout-btn {
            background-color: var(--danger-color);
            color: white;
            padding: 10px 20px;
            border-radius: 4px;
            text-decoration: none;
            font-weight: 500;
            transition: background-color 0.3s;
            display: inline-flex;
            align-items: center;
            gap: 8px;
        }

        .logout-btn:hover {
            background-color: #d32f2f;
        }

        @media (max-width: 768px) {
            .header {
                flex-direction: column;
                gap: 10px;
                text-align: center;
            }

            th, td {
                padding: 10px;
            }

            .actions {
                flex-direction: column;
                gap: 5px;
            }

            .action-btn {
                width: 100%;
                text-align: center;
                justify-content: center;
            }

            .footer {
                flex-direction: column;
                gap: 15px;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <h2>Customer Management</h2>
        <a href="<%= request.getContextPath() %>/customer/add" class="btn btn-primary">
            <i class="fas fa-plus"></i> Add New Customer
        </a>
    </div>

    <div class="content">
        <div class="table-container">
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
                    <td><span class="role-badge"><%= customer.getRole() %></span></td>
                    <td class="actions">
<%--                        <!-- Action buttons to edit or delete a customer -->--%>
<%--                        <a href="<%= request.getContextPath() %>/customer/update?id=<%= customer.getUserId() %>" class="action-btn edit-btn">--%>
<%--                            <i class="fas fa-edit"></i> Edit--%>
<%--                        </a>--%>
                        <a href="<%= request.getContextPath() %>/customer/delete?id=<%= customer.getUserId() %>"
                           onclick="return confirm('Are you sure you want to delete this customer?');"
                           class="action-btn delete-btn">
                            <i class="fas fa-trash-alt"></i> Delete
                        </a>
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
        </div>
    </div>

    <div class="footer">
        <a href="<%= request.getContextPath() %>/customer/logout" class="logout-btn">
            <i class="fas fa-sign-out-alt"></i> Logout
        </a>
    </div>
</div>
</body>
</html>