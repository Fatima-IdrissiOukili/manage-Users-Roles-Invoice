<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Password Mismatch</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <div class="alert alert-danger" role="alert">
            <h4 class="alert-heading">Password Mismatch</h4>
            <p>The entered passwords do not match. Please make sure to enter the same password in both fields.</p>
            <hr>
          
            
         <p class="mb-0">Return to <a href="javascript:history.back()">previous page</a> to correct the passwords.</p>
          
            <!-- <a href="${request.getHeader("referer")}" class="alert-link">Return to previous page</a>-->
       
        </div>
    </div>
</body>
</html>
