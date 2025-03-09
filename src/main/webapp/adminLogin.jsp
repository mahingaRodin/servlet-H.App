<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Login</title>
</head>
<body>
<h2>Admin Login</h2>

<!-- Form to log in -->
<form action="<%= request.getContextPath() %>/admin/login" method="POST">
    <label for="email">Email:</label><br>
    <input type="email" id="email" name="email" required><br><br>

    <label for="password">Password:</label><br>
    <input type="password" id="password" name="password" required><br><br>

    <input type="submit" value="Login">
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

<!-- Link to go back to the home page -->
<br>
<a href="<%= request.getContextPath() %>/index.jsp">Back to Home</a>
</body>
</html>
