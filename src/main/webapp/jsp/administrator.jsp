<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 02.08.2017
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>AdministratorPage</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <script src="../js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">

        <h1>Order list</h1>
        <table class="table table-striped">
            <th>
                <tr>
                    <td>#</td>
                    <td>Beds</td>
                    <td>Stars</td>
                    <td>Check-in date</td>
                    <td>Check-out date</td>
                    <td>Comment</td>
                    <td>Complete!</td>
                </tr>
            </th>
            <c:forEach var="request" items="${allRequests}">
                <tr>
                    <form action="/administrator" method="post">
                        <td>${request.number}</td>
                        <td>${request.beds}</td>
                        <td>${request.classID}</td>
                        <td>${request.dateFrom}</td>
                        <td>${request.dateTo}</td>
                        <td>${request.comments}</td>
                        <td><a href="/appointRoom?requestNumber=${request.number}">Appoint room</a></td>
                    </form>

                </tr>
            </c:forEach>
        </table>

</div>

<div class="container">

    <h1>Order list</h1>
    <table class="table table-striped">
        <th>
            <tr>
                <td>#</td>
                <td>Beds</td>
                <td>Stars</td>
                <td>Check-in date</td>
                <td>Check-out date</td>
                <td>Comment</td>
                <td>Complete!</td>
            </tr>
        </th>
        <c:forEach var="request" items="${unHendls}">
            <tr>
                <form action="/administrator" method="post">
                    <td>${request.number}</td>
                    <td>${request.beds}</td>
                    <td>${request.classID}</td>
                    <td>${request.dateFrom}</td>
                    <td>${request.dateTo}</td>
                    <td>${request.comments}</td>
                    <%--<td><a href="/appointRoom?requestNumber=${request.number}">Appoint room</a></td>--%>
                </form>

            </tr>
        </c:forEach>
    </table>

</div>

</body>
</html>
