<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Home Page</title>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="localization.locale" var="loc"/>
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
            <a class="navbar-brand" href="#"></a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <form class="navbar-form navbar-right">
                <fmt:message bundle="${loc}" key="button.sign_up" var="btn_signUp"/>
                <a class="btn btn-success btn-md" href="/registration" role="button">${btn_signUp}</a>
                <fmt:message bundle="${loc}" key="button.sign_in" var="btn_signIn"/>
                <a class="btn btn-success btn-md" href="/login" role="button">${btn_signIn}</a>
            </form>
        </div><!--/.navbar-collapse -->
    </div>
</nav>
<c:if test="${not empty message}">
    <div class="alert alert-success">
            ${message}
    </div>
</c:if>
<div class="jumbotron">
    <div class="container">
        <h1>Welcome!</h1>
        <h3>Lang: ${sessionScope.lang}</h3>
        <c:choose>
            <c:when test="${sessionScope.user == null}">
                <p>Wanna book a room? Log in or sign up!</p>
                <a class="btn btn-success btn-lg" href="/registration" role="button">Sign up &raquo;</a>
                <a class="btn btn-success btn-lg" href="/login" role="button">Log in &raquo;</a>
            </c:when>
            <c:otherwise>
                <p>Reserve room just now!</p>
                <a class="btn btn-success btn-lg" href="/order" role="button">Reserve room! &raquo;</a>
                <form action="Logout">
                    <button type="submit" class="btn btn-primary btn-lg">Logout</button>
                </form>
            </c:otherwise>
        </c:choose>

    </div>
</div>
</body>
</html>