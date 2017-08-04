<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 01.08.2017
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order Page</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <script src="../js/bootstrap.min.js"></script>
</head>
<body>
<%--
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Project name</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <form class="navbar-form navbar-right">
                <div class="form-group">
                    <input type="text" placeholder="Login" class="form-control">
                </div>
                <div class="form-group">
                    <input type="password" placeholder="Password" class="form-control">
                </div>
                <button type="submit" class="btn btn-success">Log in</button>
            </form>
        </div><!--/.navbar-collapse -->
    </div>
</nav>--%>
<div class="container">
    <c:if test="${not empty message}">
        <div class="alert alert-success">
                ${message}
        </div>
    </c:if>
    <form style="width: 400px" action="/order" method="post">
        <h2>Book!</h2>
        <label>How many beds?</label>
        <input type="number" name="beds" class="form-control">
        <label>How many stars?</label>
        <input type="number" name="stars" class="form-control">
        <label>Check-in date</label>
        <input type="date" name="checkIn" class="form-control">
        <label>Check-out date</label>
        <input type="date" name="checkOut" class="form-control">
        <br>
        <button type="submit" class="btn btn-primary btn-md">Reserve!</button>
    </form>
    <form action="Logout">
        <button type="submit" class="btn btn-primary btn-md">Logout</button>
    </form>
</div>

</body>
</html>
