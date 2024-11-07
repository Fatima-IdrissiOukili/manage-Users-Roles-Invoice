<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" type="text/css" href="styles.css"> <!-- Lien vers votre feuille de styles CSS -->
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            
        }
        header {
            background-color: #333;
            color: white;
            text-align: center;
            padding: 1em;
        }
        main {
            max-width: 800px;
            margin: 0 auto;
            padding: 2em;
           
        }
        .profile-section {
            border: 1px solid #ddd;
            padding: 1em;
            background-color: #f5f5f5;
            margin-bottom: 1em;
        }
        .button-section a {
            display: inline-block;
            background-color: #333;
            color: white;
            text-decoration: none;
            padding: 0.5em 1em;
            margin-right: 1em;
            border-radius: 4px;
        }
        
    </style>
     
</head>
<body>
    <header>
        <h1>User Profile</h1>
    </header>
    <main>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
        <section class="profile-section">
        
            <h2>Welcome, <c:out value="${user.getUsername()}" /> !</h2>
            <p>Email: <c:out value="${user.getEmail()}" /> </p>
            <p>Role: <c:out value="${user.getRoleName()}" /> </p>

           
        </section>
        
        <section class="button-section">
            <a href="/AppEv/changeUsername">Edit UserName</a>
            <a href="/AppEv/changePassword">Change Password</a>
            <a href="/AppEv/userInvoices" >Mes Factures</a>
            <a href="/AppEv/servletDeconnection">Se deconnecter</a>
        </section>
    </main>
    
   
</body>
</html>
