<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Invoices</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
    <div class="container mt-5">
        <h1 class="mb-4">User Invoices</h1>
        <table class="table">
            <thead>
                <tr>
                    <th>Invoice Number</th>
                    <th>Description</th>
                    <th>Amount</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="invoice" items="${invoices}">
                    <tr>
                        <td>${invoice.invoiceNumber}</td>
                        <td>
                            <table class="table table-sm">
                                <thead>
                                    <tr>
                                        <th>Description</th>
                                        <th>Amount</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="line" items="${invoice.invoiceLines}">
                                        <tr>
                                            <td>${line.description}</td>
                                            <td>${line.amount}$</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
         <hr class="my-4">
        <p class="font-weight-bold text-success mt-4">Total Amount: ${totalAmount}$</p>
         <hr class="my-4">
    </div>
</body>
</html>

