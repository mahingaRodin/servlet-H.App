<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String language = request.getParameter("lang");
    if (language == null) {
        language = "en";
    }
    Locale locale = new Locale(language);
    ResourceBundle bundle = ResourceBundle.getBundle("message", locale);
%>

<html>
<head>
    <title><%= bundle.getString("login.title") %></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        :root {
            --primary-color: #4361ee;
            --primary-hover: #3a56d4;
            --secondary-color: #f8f9fa;
            --text-color: #333;
            --border-color: #e0e0e0;
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
            background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
        }

        .login-container {
            width: 100%;
            max-width: 400px;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
            overflow: hidden;
            position: relative;
        }

        .language-select {
            position: absolute;
            top: 15px;
            right: 15px;
            z-index: 10;
        }

        .language-select select {
            padding: 5px 10px;
            border: 1px solid var(--border-color);
            border-radius: 4px;
            background-color: white;
            font-size: 0.9rem;
            cursor: pointer;
        }

        .language-select select:focus {
            outline: none;
            border-color: var(--primary-color);
        }

        .login-header {
            background-color: var(--primary-color);
            color: white;
            padding: 30px 20px;
            text-align: center;
        }

        .login-header h2 {
            font-weight: 600;
            font-size: 1.8rem;
            margin: 0;
        }

        .login-form {
            padding: 30px;
        }

        .form-group {
            margin-bottom: 20px;
            position: relative;
        }

        .form-group i {
            position: absolute;
            left: 12px;
            top: 50%;
            transform: translateY(-50%);
            color: #888;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: 500;
        }

        input {
            width: 100%;
            padding: 12px 12px 12px 40px;
            border: 1px solid var(--border-color);
            border-radius: 4px;
            font-size: 16px;
            transition: border-color 0.3s;
        }

        input:focus {
            outline: none;
            border-color: var(--primary-color);
            box-shadow: 0 0 0 2px rgba(67, 97, 238, 0.2);
        }

        .btn {
            display: block;
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

        .signup-link {
            text-align: center;
            margin-top: 20px;
            font-size: 0.9rem;
        }

        .signup-link a {
            color: var(--primary-color);
            text-decoration: none;
            font-weight: 500;
        }

        .signup-link a:hover {
            text-decoration: underline;
        }

        @media (max-width: 500px) {
            .login-form {
                padding: 20px;
            }

            .login-header {
                padding: 20px;
            }

            .login-header h2 {
                font-size: 1.5rem;
            }
        }
    </style>
</head>
<body>
<div class="login-container">
    <!-- Language Selection Form -->
    <div class="language-select">
        <form method="GET" id="langForm">
            <select name="lang" id="lang" onchange="this.form.submit()">
                <option value="en" <%= "en".equals(language) ? "selected" : "" %>>English</option>
                <option value="fr" <%= "fr".equals(language) ? "selected" : "" %>>Français</option>
                <option value="kiny" <%= "kiny".equals(language) ? "selected" : "" %>>Kinyarwanda</option>
            </select>
        </form>
    </div>

    <div class="login-header">
        <h2><%= bundle.getString("login.title") %></h2>
    </div>

    <div class="login-form">
        <!-- Login Form -->
        <form action="<%= request.getContextPath() %>/admin/login" method="post">
            <div class="form-group">
                <label for="fullName"><%= bundle.getString("login.full_name") %></label>
                <i class="fas fa-user"></i>
                <input type="text" id="fullName" name="fullName" placeholder="<%= bundle.getString("login.full_name") %>" required>
            </div>

            <div class="form-group">
                <label for="password"><%= bundle.getString("login.password") %></label>
                <i class="fas fa-lock"></i>
                <input type="password" id="password" name="password" placeholder="<%= bundle.getString("login.password") %>" required>
            </div>

            <button type="submit" class="btn"><%= bundle.getString("login.button") %></button>
        </form>

        <!-- Navigation Links -->
        <div class="signup-link">
            <p><a href="adminSignup.jsp">Sign Up</a></p>
        </div>
    </div>
</div>
</body>
</html>