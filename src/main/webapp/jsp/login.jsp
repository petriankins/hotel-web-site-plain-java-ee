<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Окно логина</title>
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
    <form style="width:200px" action="/login" method="post">
        <div class="form-group">
            <label>Login</label>
            <input type="email" name="login" class="form-control" placeholder="Type your email">
        </div>
        <div class="form-group">
            <label>Password</label>
            <input type="password" name="password" class="form-control" placeholder="Password">
        </div>
        <button type="submit" class="btn btn-primary btn-md">Log In</button>
    </form>
</div>
</body>
</html>
