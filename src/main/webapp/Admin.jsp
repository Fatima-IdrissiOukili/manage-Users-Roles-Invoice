
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" type="text/css" href="styles.css"> <!-- Lien vers votre feuille de styles CSS -->
    <style>
       header {
            background-color: #333;
            color: white;
            text-align: center;
            padding: 1em;
        }
        .admin-button {
            display: inline-block;
            padding: 10px 20px;
            margin: 10px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
          
    </style>
</head>
<body>
    <header>
        <h1>Admin Dashboard</h1>
    </header>
    
    
    <main>
        <section class="admin-section">
            <h2>Welcome, Admin!</h2>
            <br>
            <p>Choose an action:</p>
            
            <a class="admin-button" href="users?action=list">Manage Users</a>
          <!--<a class="admin-button" href="/AppEv/RoleController?action=/list">Manage Roles</a>-->
           <a class="admin-button" href="roles?action=/list">Manage Roles</a>
            <a class="admin-button" href="invoices?action=/list">Manage Invoices</a>
           <a class="admin-button" href="servletDeconnection">Se deconnecter</a>
        </section>
    </main>
    <br>
    
   
</body>
</html>
