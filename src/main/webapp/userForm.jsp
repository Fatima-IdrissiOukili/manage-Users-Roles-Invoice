<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>User Management Application</title>
    <link rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
        crossorigin="anonymous">
</head>
<body>

    <header>
        <nav class="navbar navbar-expand-md navbar-dark"
            style="background-color: tomato">
            <div>
                <a href="https://www.javaguides.net" class="navbar-brand"> Manage<b>User</b> </a>
            </div>

            <ul class="navbar-nav">
                <li><a href="<%=request.getContextPath()%>/users?action=/list"
                    class="nav-link">Users</a></li>
            </ul>
        </nav>
    </header>
    <br>
    <div class="container col-md-5">
        <div class="card">
            <div class="card-body">
                <c:choose>
                    <c:when test="${param.action == '/editUser'}">
                        <form action="<c:url value='/users?action=/updateUser' />" method="post">
                            <caption>
                                <h2>Edit User</h2>
                            </caption>
                            <input type="hidden" name="id" value="<c:out value='${user.id}' />" />
                            <fieldset class="form-group">
                                <label>User Name</label> <input type="text"
                                    value="<c:out value='${user.username}' />" class="form-control"
                                    name="username" required="required">
                            </fieldset>
                            <fieldset class="form-group">
                                <label>User Email</label> <input type="text"
                                    value="<c:out value='${user.email}' />" class="form-control"
                                    name="email">
                            </fieldset>
                            <fieldset class="form-group">
                                <label>User password</label> <input type="text"
                                    value="<c:out value='${user.password}' />" class="form-control"
                                    name="password">
                            </fieldset>
                            <fieldset class="form-group">
                                <label>User Active</label> <input type="text"
                                    value="<c:out value='${user.active}' />" class="form-control"
                                    name="active">
                            </fieldset>
                            <fieldset class="form-group">
                            <button type="submit" class="btn btn-success" name="action" value="editUser">Save</button>
                           </fieldset>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <form action="<c:url value='/users?action=/addUser' />" method="post">
                            <caption>
                                <h2>Add New User</h2>
                            </caption>
                            <!-- Les champs pour l'ajout vont ici -->
                            <fieldset class="form-group">
                                <label>User Name</label> <input type="text"
                                    class="form-control"
                                    name="username" required="required">
                            </fieldset>
                            <fieldset class="form-group">
                                <label>User Email</label> <input type="text"
                                    class="form-control"
                                    name="email">
                            </fieldset>
                            <fieldset class="form-group">
                                <label>User password</label> <input type="text"
                                    class="form-control"
                                    name="password">
                            </fieldset>
                            <fieldset class="form-group">
                                <label>User Active</label> <input type="text"
                                    class="form-control"
                                    name="active">
                            </fieldset>
                            <fieldset class="form-group">
                            <button type="submit" class="btn btn-success" >Save</button>
                            </fieldset>
                        </form>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</body>
</html>
