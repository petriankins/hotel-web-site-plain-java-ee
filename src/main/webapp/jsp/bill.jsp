<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 08.08.2017
  Time: 12:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="localization.locale" var="loc"/>
    <fmt:message bundle="${loc}" key="billPage.title" var="billPageTitle"/>
    <title>${billPageTitle}</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <script src="../js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <fmt:message bundle="${loc}" key="billPage.title" var="billPageTitle"/>
    <h1>${billPageTitle}</h1>
    <table class="table table-striped">
        <fmt:message bundle="${loc}" key="reqestsNumber" var="reqestsNumber"/>
        <tr><td>${reqestsNumber}</td><td>${request.number}</td></tr>
        <fmt:message bundle="${loc}" key="checkInDate" var="checkInDate"/>
        <tr><td>${checkInDate}</td><td><fmt:formatDate value="${request.dateFrom}" pattern="yyyy-MM-dd" /></td></tr>
        <fmt:message bundle="${loc}" key="checkOutDate" var="checkOutDate"/>
        <tr><td>${checkOutDate}</td><td><fmt:formatDate value="${request.dateTo}" pattern="yyyy-MM-dd" /></td></tr>
        <fmt:message bundle="${loc}" key="billsNumber" var="billsNumber"/>
        <tr><td>${billsNumber}</td><td>${bill.number}</td></tr>
        <fmt:message bundle="${loc}" key="roomsNumber" var="roomsNumber"/>
        <tr><td>${roomsNumber}</td><td>${room.number}</td></tr>
        <fmt:message bundle="${loc}" key="totalPrice" var="totalPrice"/>
        <tr><td>${totalPrice}</td><td>${bill.sum}</td></tr>
    </table>
    <fmt:message bundle="${loc}" key="bakToOrderListBtn" var="bakToOrderListBtn"/>
    <p><a class="btn btn-primary btn-md" href="/administrator" role="button">${bakToOrderListBtn}</a></p>

</div>

</body>
</html>
