<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 31.07.2017
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Page</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <script src="../js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <c:if test="${not empty message}">
        <div class="alert alert-danger">
                ${message}
        </div>
    </c:if>
    <form style="width: 400px" action="/registration" method="post">
        <div class="form-group">
            <label>First name:</label>
            <input type="text" name="name" class="form-control" placeholder="First Name">
        </div>
        <div class="form-group">
            <label>Last name:</label>
            <input type="text" name="lastName" class="form-control" placeholder="Last Name">
        </div>
        <div class="form-group">
            <label>Email:</label>
            <input type="email" name="login" class="form-control" placeholder="Type your email">
        </div>
        <div class="form-group">
            <label>Password:</label>
            <input type="password" name="password" class="form-control" placeholder="Password">
        </div>
        <button type="submit" class="btn btn-primary btn-md">Sign up</button>

        <!--<p><a class="btn btn-primary btn-md" href="/" role="button">Sign up</a></p>-->
    </form>
</div>
</body>
</html>
