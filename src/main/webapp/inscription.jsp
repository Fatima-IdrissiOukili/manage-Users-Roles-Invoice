<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Page d'Inscription</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet">
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

        .login-container {
            background-color: #ffffff;
            border-radius: 5px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
            padding: 40px;
            width: 650px;
            text-align: center;
        }

        .login-container h1 {
            margin-buttom: 25px;
        }

        .login-form label {
            display: block;
            margin-bottom: 8px;
        }

        .login-form input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin-bottom: 15px;
        }

        .login-form .custom-btn {
            background-color: #f0f0f0;
            color: #000;
            border: none;
            cursor: pointer;
            width: 100%; /* Adjust the width as needed */
            margin: 0 auto; /* Center the button */
        }

        .login-form .custom-btn:hover {
            background-color: #ddd;
        }
        
        .placeicon {
	font-family: fontawesome
}
   .signup-image {
            margin: 0 auto; /* Ajustez la marge horizontale selon vos besoins */
            width: 50%;
            overflow: hidden;
        }
        .wide-input {
            width: 100%; /* Vous pouvez ajuster cette valeur selon vos besoins */
        }
        
    </style>
</head>
<body class="snippet-body bg-info">

        			
    <div class="login-container" style="display: flex; align-items: center;">
        
    <div class="signup-image">
            <img src="images/signup-image.jpg" alt="sing up image" >
        </div>	
        
       
        <form class="login-form form-horizontal" action="inscription" method="post" style="padding: 0 20px;">
            <div class="form-group">
                <label for="username">Nom d'utilisateur:</label>
                <input type="text" id="username" name="username" required class="form-control border-info placeicon wide-input">
            </div>

            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required class="form-control border-info placeicon wide-input" >
            </div>

            <div class="form-group">
                <label for="password">Mot de passe:</label>
                <input type="password" id="password" name="password" required class="form-control border-info placeicon wide-input">
            </div>
            
             <div class="form-group row justify-content-center">
             <div class="col-6 px-6 mt-6">
            <input type="submit" value="S'inscrire" class="btn btn-block btn-info" style="width: 90px;">
            </div>
           </div>
           
          </form>
    </div>
 
</body>
</html>

