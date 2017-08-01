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
    <form style="width: 400px" action="/registration" method="post">
        <div class="form-group">
            <label>Name:</label>
            <input type="text" name="userName" class="form-control" placeholder="Name">
        </div>
        <div class="form-group">
            <label>Email:</label>
            <input type="email" name="email" class="form-control" placeholder="Email">
        </div>
        <div class="form-group">
            <label>Login</label>
            <input type="text" name="login" class="form-control" placeholder="Login">
        </div>
        <div class="form-group">
            <label>Password</label>
            <input type="password" name="password" class="form-control" placeholder="Password">
        </div>
        <button type="submit" class="btn btn-primary btn-md">Sign Up</button>
    </form>
</div>

</body>
</html>
