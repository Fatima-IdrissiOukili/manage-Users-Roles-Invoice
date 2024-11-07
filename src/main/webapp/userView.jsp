
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <title>User Management</title>
    
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
	
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand"> User
					Management App </a>
			</div>

			<ul class="navbar-nav">
			<li><a href="<%=request.getContextPath()%>/Admin.jsp"
		    class="nav-link">Home</a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">List of Users</h3>
			<hr>
			<div class="container text-left">

				<a href="<c:url value='/users?action=/new' />" class="btn btn-success">Add
					New User</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Email</th>
						<th>Password</th>
						<th>Activé</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="user" items="${listUser}">

						<tr>
							<td><c:out value="${user.id}" /></td>
							<td><c:out value="${user.username}" /></td>
							<td><c:out value="${user.email}" /></td>
							<td><c:out value="${user.password}" /></td>
							<td>${user.active ? 'Actif' : 'Inactif'}</td>
                           
							<td>
							 <a href="<c:url value='/users?action=/editUser&id=${user.id}' />">
                              <button class="btn btn-primary btn-sm" title="Modifier">
                             <i class="fas fa-edit"></i> </button> </a>
                             
                             
                            <a href="<c:url value='/users?action=/deleteUser&id=${user.id}' />">
                            <button class="btn btn-danger btn-sm" title="Supprimer">
                            <i class="fas fa-trash"></i> </button></a>
                                
                              <a href="<c:url value='/users?action=/activateUser&id=${user.id}' />">
                              <button class="btn btn-success btn-sm" title="Activer">
                                 <i class="fas fa-check"></i> </button></a>
                                 
                         <a href="<c:url value='/users?action=/desactivateUser&id=${user.id}' />">
                           <button class="btn btn-warning btn-sm" title="Désactiver">
                                 <i class="fas fa-times"></i> </button> </a>
                                 
						     </td>
						</tr>
					</c:forEach>
					
				</tbody>
				    
			</table>
		</div>
	</div>
</body>
</html>

