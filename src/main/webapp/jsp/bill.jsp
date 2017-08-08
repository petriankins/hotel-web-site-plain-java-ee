<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 08.08.2017
  Time: 12:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Bill Page</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <script src="../js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h1>Bill</h1>
    <table class="table table-striped">
        <tr><td>Request's #:</td><td>${request.number}</td></tr>
        <tr><td>Bill's #:</td><td>${bill.number}</td></tr>
        <tr><td>Room's #:</td><td>${room.number}</td></tr>
        <tr><td>Total Price:</td><td>${bill.sum}</td></tr>
    </table>

</div>


</body>
</html>
