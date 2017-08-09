<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 07.08.2017
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Appoint room</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <script src="../js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">

    <h1>Order</h1>
    <table class="table table-striped">
        <th>
            <tr>
                <td>#</td>
                <td>Beds</td>
                <td>Stars</td>
                <td>Check-in date</td>
                <td>Check-out date</td>
                <td>Comment</td>
            </tr>
        <th>
            <tr>
                <td>${request.number}</td>
                <td>${request.beds}</td>
                <td>${request.classID}</td>
                <td><fmt:formatDate value="${request.dateFrom}" pattern="yyyy-MM-dd" /></td>
                <td><fmt:formatDate value="${request.dateTo}" pattern="yyyy-MM-dd" /></td>
                <td>${request.comments}</td>
            </tr>
        </th>
    </table>
    <h1>Available rooms</h1>

    <form style="width:200px" action="/bill" method="post">
        <table class="table table-striped">
            <tr>
                <td>Number</td>
                <td>Beds</td>
                <td>Stars</td>
                <td>Choose</td>

            </tr>
            <c:forEach var="room" items="${availableRooms}">
                <tr>
                    <td>${room.number}</td>
                    <td>${room.beds}</td>
                    <td>${room.roomClass}</td>
                    <td><input type="radio" name="roomNumber" value="${room.number}"/></td>
                </tr>
            </c:forEach>
        </table>
       <button type="submit" class="btn btn-primary btn-md">Bill</button>
   </form>

</div>
</body>
</html>
