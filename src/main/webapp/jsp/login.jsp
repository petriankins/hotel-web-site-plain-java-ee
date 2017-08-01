<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Окно логина</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <script src="../js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <form style="width:200px" action="/login" method="post">
        <div class="form-group">
            <label>Login</label>
            <input type="text" name="login" class="form-control" placeholder="Login">
        </div>
        <div class="form-group">
            <label>Password</label>
            <input type="password" name="password" class="form-control" placeholder="Password">
        </div>
        <button type="submit" class="btn btn-primary btn-md">Log In</button>
        <button type="submit" class="btn btn-primary btn-md"><a href="/jsp/registration.jsp">Sign Up</a></button>
    </form>
</div>
</body>
</html>
