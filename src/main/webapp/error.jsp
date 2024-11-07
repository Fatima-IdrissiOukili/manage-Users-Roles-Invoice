<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Error Page</title>
    <link rel="stylesheet" type="text/css" href="styles.css"> <!-- Lien vers votre feuille de styles CSS -->
    <style>
        /* Styles spécifiques à cette page d'erreur */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        header {
            background-color: #333;
            color: #fff;
            text-align: center;
            padding: 1em 0;
        }
        main {
            max-width: 800px;
            margin: 0 auto;
            padding: 2em;
        }
        .error-section {
            background-color: #fff;
            border: 1px solid #ddd;
            padding: 2em;
            text-align: center;
        }
        .button-section {
            text-align: center;
            margin-top: 1em;
        }
        footer {
            background-color: #333;
            color: #fff;
            text-align: center;
            padding: 1em 0;
            position: absolute;
            bottom: 0;
            width: 100%;
        }
    </style>
</head>
<body>
    <header>
        <h1>Error Page</h1>
    </header>
    <main>
        <section class="error-section">
            <h2>An Error Occurred</h2>
            <p>We're sorry, but an error occurred while processing your request.</p>
            <p>Please try again later or contact our support team for assistance.</p>
        </section>
        <section class="button-section">
            <a href="login.html">Back to Home</a>
        </section>
    </main>
    <footer>
        <p>&copy; 2023 Your Company</p>
    </footer>
</body>
</html>
