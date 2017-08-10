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
<%@ taglib prefix="ht" uri="/WEB-INF/headerTag.tld" %>

<html>
<head>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="localization.locale" var="loc"/>
    <fmt:message bundle="${loc}" key="appointRoom.title" var="appointRoomTitle"/>
    <title>${appointRoomTitle}</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/custom.css">
</head>
<body>
<ht:HeaderTag/>
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
                <td><fmt:formatDate value="${request.dateFrom}" pattern="dd-MM-yyyy" /></td>
                <td><fmt:formatDate value="${request.dateTo}" pattern="dd-MM-yyyy" /></td>
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
                    <td><input type="radio" id="radButton" name="roomNumber" onchange="activateButton()" value="${room.number}"/></td>
                </tr>
            </c:forEach>
        </table>
        <fmt:message bundle="${loc}" key="bill.button" var="bill"/>
        <button type="submit" id="subButton" class="btn btn-primary btn-md" disabled="true">${bill}</button>
        <script>
            function activateButton() {
                var radios = document.getElementsByTagName('input');
                for(var i = 0; i < radios.length; i++) {
                    if(radios[i].type === 'radio' && radios[i].checked){
                        document.getElementById('subButton').disabled = false;
                    }
                }
            }
        </script>
    </form>
    <fmt:message bundle="${loc}" key="bakToOrderListBtn" var="bakToOrderListBtn"/>
    <p><a class="btn btn-primary btn-md" href="/administrator" role="button">${bakToOrderListBtn}</a></p>

</div>
</body>
</html>
