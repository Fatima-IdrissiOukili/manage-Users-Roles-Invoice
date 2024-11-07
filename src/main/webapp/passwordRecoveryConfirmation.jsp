<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Password Recovery Confirmation</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .confirmation-container {
            background-color: #ffffff;
            border-radius: 5px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
            padding: 20px;
            width: 300px;
            text-align: center;
        }

        .confirmation-container h2 {
            margin-bottom: 20px;
        }

        .confirmation-message {
            margin-bottom: 15px;
        }

        .back-to-login {
            text-align: center;
            margin-top: 10px;
        }

        .back-to-login a {
            color: #007bff;
            text-decoration: none;
        }

        .back-to-login a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="confirmation-container">
        <h2>Password Recovery Confirmation</h2>
        <p class="confirmation-message">Your password reset request has been received.</p>
        <p class="confirmation-message">An email with instructions on how to reset your password has been sent to your registered email address. Please check your inbox and follow the instructions provided.</p>
        <p class="confirmation-message">If you do not receive an email within a few minutes, please check your spam folder or contact our support team.</p>
        <div class="back-to-login">
            <a href="login.html">Back to Login</a>
        </div>
    </div>
</body>
</html>
