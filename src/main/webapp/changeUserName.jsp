
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Change Username</title>
  
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h1 class="mb-4">Change Username</h1>
        
        <form method="post" action="changeUsername" class="col-md-6">
            <div class="form-group">
             <input type="hidden" name="userId" value="${user.id}">
                <label for="newUsername">New Username:</label>
                <input type="text" id="newUsername" name="newUsername" class="form-control"  value="${user.username}" required>
            </div>
            <button type="submit" class="btn btn-primary">Change Username</button>
        </form>
    </div>

    
</body>
</html>
