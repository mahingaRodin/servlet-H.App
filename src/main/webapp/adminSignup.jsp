<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Signup</title>
</head>
<body>
<h2>Admin Signup</h2>

<!-- Form to sign up a new admin -->
<form action="<%= request.getContextPath() %>/admin/signup" method="POST">
    <label for="fullName">Full Name:</label><br>
    <input type="text" id="fullName" name="fullName" required><br><br>

    <label for="email">Email:</label><br>
    <input type="email" id="email" name="email" required><br><br>

    <label for="password">Password:</label><br>
    <input type="password" id="password" name="password" required><br><br>

    <label for="confirmPassword">Confirm Password:</label><br>
    <input type="password" id="confirmPassword" name="confirmPassword" required><br><br>

    <input type="submit" value="Sign Up">
</form>

<!-- Display error message if there is one -->
<%
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null) {
%>
<p style="color: red;"><%= errorMessage %></p>
<%
    }
%>

<!-- Link to go back to the login page -->
<br>
<a href="<%= request.getContextPath() %>/admin/login">Already have an account? Login here</a>
</body>
</html>
