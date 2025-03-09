<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Customer</title>
</head>
<body>
<h2>Add Customer</h2>

<!-- Form to add a new customer -->
<form action="<%= request.getContextPath() %>/customer/add" method="POST">
    <label for="fullName">Full Name:</label><br>
    <input type="text" id="fullName" name="fullName" required><br><br>

    <label for="email">Email:</label><br>
    <input type="email" id="email" name="email" required><br><br>

    <label for="phoneNumber">Phone Number:</label><br>
    <input type="text" id="phoneNumber" name="phoneNumber"><br><br>

    <label for="role">Role:</label><br>
    <select id="role" name="role" required>
        <option value="customer">Customer</option>
        <option value="admin">Admin</option>
    </select><br><br>

    <input type="submit" value="Add Customer">
</form>

<!-- Link to go back to the customer list -->
<br>
<a href="<%= request.getContextPath() %>/customer/list">Back to Customer List</a>
</body>
</html>
