<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Invoice Management</title>
    
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
            <a href="https://www.javaguides.net" class="navbar-brand">Invoice Management App</a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<c:url value='/Admin.jsp' />" class="nav-link">Home</a></li>
        </ul>
    </nav>
</header>
<br>

<div class="row">
    <!--<div class="alert alert-success" *ngIf='message'>{{message}}</div>-->

    <div class="container">
        <h3 class="text-center">List of Invoices</h3>
        <hr>
        <div class="container text-left">
            <a href="<c:url value='/invoices?action=/new' />" class="btn btn-success">Add New Invoice</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>User ID</th>
                <th>Invoice Number</th>
                <th>Description</th>
                <th>Amount</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="invoice" items="${listInvoice}">
                <tr>
                    <td><c:out value="${invoice.id}" /></td>
                    <td><c:out value="${invoice.user.id}" /></td>
                    <td><c:out value="${invoice.invoiceNumber}" /></td>
                    <td>
                        <c:forEach var="invoiceline" items="${invoice.invoiceLines}">
                            <c:out value="${invoiceline.description}" /><br/>
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach var="invoiceline" items="${invoice.invoiceLines}">
                            <c:out value="${invoiceline.amount}"/>$
                        </c:forEach>
                    </td>  
                    <td>
                        <a href="<c:url value='/invoices?action=/editInvoice&amp;id=${invoice.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="<c:url value='/invoices?action=/deleteInvoice&amp;id=${invoice.id}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>


