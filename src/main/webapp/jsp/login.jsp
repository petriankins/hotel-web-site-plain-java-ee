<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Окно логина</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <script src="../js/bootstrap.min.js"></script>
</head>
<body>
<form>
    Login: <input type="text" name="login"/>
    Password: <input type="text" name="pass"/>
    <input type="submit" value="Log in"/>

</form>
<form action="/registration" method="get">
    <input type="submit" value="Sign up"/>
</form>
</body>
</html>
