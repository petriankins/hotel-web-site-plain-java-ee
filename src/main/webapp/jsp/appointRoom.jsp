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
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="localization.locale" var="loc"/>
    <fmt:message bundle="${loc}" key="appointRoom.title" var="appointRoomTitle"/>
    <title>${appointRoomTitle}</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <script src="../js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">

    <fmt:message bundle="${loc}" key="order.table" var="orderTable"/>
    <h1>${orderTable}</h1>
    <table class="table table-striped">
        <tr>
            <td>#</td>
            <fmt:message bundle="${loc}" key="beds" var="beds"/>
            <td>${beds}</td>
            <fmt:message bundle="${loc}" key="stars" var="stars"/>
            <td>${stars}</td>
            <fmt:message bundle="${loc}" key="checkInDate" var="checkInDate"/>
            <td>${checkInDate}</td>
            <fmt:message bundle="${loc}" key="checkOutDate" var="checkOutDate"/>
            <td>${checkOutDate}</td>
            <fmt:message bundle="${loc}" key="comment" var="comment"/>
            <td>${comment}</td>
        </tr>
            <tr>
                <td>${request.number}</td>
                <td>${request.beds}</td>
                <td>${request.classID}</td>
                <td>${request.dateFrom}</td>
                <td>${request.dateTo}</td>
                <td>${request.comments}</td>
            </tr>
    </table>
    <fmt:message bundle="${loc}" key="availableRooms.table" var="availableRoomsTable"/>
    <h1>${availableRoomsTable}</h1>

    <form style="width:200px" action="/bill" method="post">
        <table class="table table-striped">
            <tr>
                <fmt:message bundle="${loc}" key="number" var="number"/>
                <td>${number}</td>
                <fmt:message bundle="${loc}" key="beds" var="beds"/>
                <td>${beds}</td>
                <fmt:message bundle="${loc}" key="stars" var="stars"/>
                <td>${stars}</td>
                <fmt:message bundle="${loc}" key="choose.row" var="choose"/>
                <td>${choose}</td>
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
        <fmt:message bundle="${loc}" key="bill.button" var="bill"/>
        <button type="submit" class="btn btn-primary btn-md">${bill}</button>
    </form>

</div>
</body>
</html>
