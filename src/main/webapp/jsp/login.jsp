<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>Окно логина</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <script src="../js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <fmt:message bundle="${loc}" key="project.name" var="projectName"/>
            <a class="navbar-brand" href="/">${projectName}</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <form class="navbar-form navbar-right" action="/login" method="post">
                <div class="form-group">
                    <fmt:message bundle="${loc}" key="email" var="email"/>
                    <input type="email" name="login" placeholder="${email}" class="form-control">
                </div>
                <div class="form-group">
                    <fmt:message bundle="${loc}" key="login.password_label" var="pass"/>
                    <input type="password" name="password" placeholder="${pass}" class="form-control">
                </div>

                <fmt:message bundle="${loc}" key="button.sign_in" var="btn_signIn"/>
                <button type="submit" class="btn btn-success">${btn_signIn}</button>

                <fmt:message bundle="${loc}" key="button.sign_up" var="btn_signUp"/>
                <a class="btn btn-success btn-md" href="/registration" role="button">${btn_signUp}</a>
            </form>
        </div><!--/.navbar-collapse -->
    </div>
</nav>
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
<script src="../js/custom.js"></script>
</body>
</html>
