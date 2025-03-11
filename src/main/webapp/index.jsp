<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome to Hotel Booking</title>
    <style>
        :root {
            --primary-color: #4361ee;
            --primary-hover: #3a56d4;
            --secondary-color: #f8f9fa;
            --text-color: #333;
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
        }

        .hero {
            height: 100vh;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            text-align: center;
            padding: 0 20px;
            background: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)),
            url('https://images.unsplash.com/photo-1566073771259-6a8506099945?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80');
            background-size: cover;
            background-position: center;
            color: white;
        }

        .hero-content {
            max-width: 800px;
            margin: 0 auto;
        }

        h1 {
            font-size: 3rem;
            margin-bottom: 20px;
            font-weight: 700;
        }

        p {
            font-size: 1.2rem;
            margin-bottom: 30px;
        }

        .buttons {
            display: flex;
            gap: 20px;
            justify-content: center;
            flex-wrap: wrap;
        }

        .button {
            display: inline-block;
            padding: 15px 30px;
            background-color: var(--primary-color);
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-weight: 600;
            transition: all 0.3s ease;
            font-size: 1rem;
            border: 2px solid var(--primary-color);
        }

        .button:hover {
            background-color: var(--primary-hover);
            transform: translateY(-3px);
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
        }

        .button.outline {
            background-color: transparent;
            border: 2px solid white;
        }

        .button.outline:hover {
            background-color: white;
            color: var(--primary-color);
        }

        @media (max-width: 768px) {
            h1 {
                font-size: 2rem;
            }

            p {
                font-size: 1rem;
            }

            .button {
                padding: 12px 24px;
            }
        }
    </style>
</head>
<body>
<section class="hero">
    <div class="hero-content">
        <h1>Welcome to Our Hotel Booking System</h1>
        <p>Experience luxury and comfort with our premium hotel booking service. Find the perfect accommodation for your next adventure.</p>
        <div class="buttons">
            <a href="adminLogin.jsp" class="button">Login</a>
            <a href="adminSignup.jsp" class="button outline">Register</a>
        </div>
    </div>
</section>
</body>
</html>