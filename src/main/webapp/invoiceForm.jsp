<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Invoice Management Application</title>
    <link rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
        crossorigin="anonymous">
</head>
<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
        <div>
            <a href="#" class="navbar-brand"> Manage <b>Invoice</b> </a>
        </div>
        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/invoices?action=/list" class="nav-link">Invoices</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-6">
    <div class="card">
        <div class="card-body">
            <c:choose>
                <c:when test="${param.action == '/editInvoice'}">
                    <form action="<c:url value='/invoices?action=/updateInvoice' />" method="post">
                        <caption>
                            <h2>Edit Invoice</h2>
                        </caption>
                        <input type="hidden" name="id" value="<c:out value='${invoice.id}' />" />
                        <div class="form-group">
                            <label>User ID</label>
                            <input type="text" class="form-control" name="user_id" value="<c:out value='${invoice.user.id}' />" required>
                        </div>
                        <div class="form-group">
                            <label>Invoice Number</label>
                            <input type="text" class="form-control" name="invoiceNumber" value="<c:out value='${invoice.invoiceNumber}' />" required>
                        </div>
                        <c:forEach var="invoiceline" items="${invoice.invoiceLines}" varStatus="status">
                            <div class="form-group">
                                <label>Description</label>
                                <input type="text" class="form-control" name="description" value="<c:out value='${invoiceline.description}' />" />
                            </div>
                            <div class="form-group">
                                <label>Amount</label>
                                <input type="text" class="form-control" name="amount" value="<c:out value='${invoiceline.amount}' />" />
                            </div>
                        </c:forEach>
                        <button type="submit" class="btn btn-success">Save</button>
                    </form>
                </c:when>
                <c:otherwise>
                    <form action="<c:url value='/invoices?action=/addInvoice' />" method="post">
                        <caption>
                            <h2>Add New Invoice</h2>
                        </caption>
                        <div class="form-group">
                            <label>User ID</label>
                            <input type="text" class="form-control" name="user_id" required>
                        </div>
                        <div class="form-group">
                            <label>Invoice Number</label>
                            <input type="text" class="form-control" name="invoiceNumber" required>
                        </div>
                        <div class="form-group">
                            <label>Description</label>
                            <input type="text" class="form-control" name="description" required>
                        </div>
                        <div class="form-group">
                            <label>Amount</label>
                            <input type="text" class="form-control" name="amount" required>
                        </div>
                        <button type="submit" class="btn btn-success">Save</button>
                    </form>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
</body>
</html>
