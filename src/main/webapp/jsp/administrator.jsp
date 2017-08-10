<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 02.08.2017
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="h1" uri="/WEB-INF/headerTag.tld" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="localization.locale" var="loc"/>
    <fmt:message bundle="${loc}" key="adminPage.title" var="adminPageTitle"/>
    <title>${adminPageTitle}</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/custom.css">
    <script src="../js/bootstrap.min.js"></script>
</head>
<body>
<h1:HeaderTag/>
<div class="jumbotron">
    <div class="container">
        <fmt:message bundle="${loc}" key="unhandledRequests.table" var="unhandledReqTable"/>
        <h1>${unhandledReqTable}</h1>
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
                <td></td>
            </tr>
            <c:forEach var="request" items="${unhandledRequests}">
                <tr>
                    <form action="/administrator" method="post">
                        <td>${request.number}</td>
                        <td>${request.beds}</td>
                        <td>${request.classID}</td>
                        <td><fmt:formatDate type="date" value="${request.dateFrom}"/></td>
                        <td><fmt:formatDate type="date" value="${request.dateTo}"/></td>
                        <td>${request.comments}</td>
                        <fmt:message bundle="${loc}" key="appointRoom_btn" var="appointRoom"/>
                        <td><a href="/appointRoom?requestNumber=${request.number}">${appointRoom}</a></td>
                    </form>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<div class="container">
    <fmt:message bundle="${loc}" key="allRequests.table" var="allRequestsTable"/>
    <h1>${allRequestsTable}</h1>
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
            <td></td>
        </tr>
        <c:forEach var="request" items="${allRequests}">
            <tr>
                <form action="/administrator" method="post">
                    <td>${request.number}</td>
                    <td>${request.beds}</td>
                    <td>${request.classID}</td>
                    <td><fmt:formatDate type="date" value="${request.dateFrom}"/></td>
                    <td><fmt:formatDate type="date" value="${request.dateTo}"/></td>
                    <td>${request.comments}</td>
                </form>

            </tr>
        </c:forEach>
    </table>
</div>
<script src="http://code.jquery.com/jquery-3.2.1.min.js"
        integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
        crossorigin="anonymous">
</script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/custom.js"></script>
</body>
</html>
