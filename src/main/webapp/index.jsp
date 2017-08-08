<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmg" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Home Page</title>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="localization.locale" var="loc"/>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/custom.css">
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
            <c:choose>
                <c:when test="${sessionScope.user == null}">
                    <form class="navbar-form navbar-right" action="/login" method="post">
                        <div class="form-group">
                            <input type="email" name="login" placeholder="Email" class="form-control">
                        </div>
                        <div class="form-group">
                            <input type="password" name="password" placeholder="Password" class="form-control">
                        </div>

                        <fmt:message bundle="${loc}" key="button.sign_in" var="btn_signIn"/>
                        <button type="submit" class="btn btn-success">${btn_signIn}</button>

                        <fmt:message bundle="${loc}" key="button.sign_up" var="btn_signUp"/>
                        <a class="btn btn-success btn-md" href="/registration" role="button">${btn_signUp}</a>
                    </form>
                </c:when>
                <c:otherwise>
                    <ul class="nav navbar-nav">
                        <li>
                            <span class="navbar-item">${sessionScope.user.name}</span>
                        </li>
                        <li>
                            <fmt:message bundle="${loc}" key="button.logout" var="btn_logout"/>
                            <a href="/logout">${btn_logout}</a>
                        </li>
                    </ul>
                </c:otherwise>
            </c:choose>

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
        <fmt:message bundle="${loc}" key="home.welcome" var="welcome"/>
        <h1>${welcome}</h1>
    </div>
    <c:if test="${sessionScope.user != null}">
        <div class="container">
            <form action="/order" method="post">
                <fmt:message bundle="${loc}" key="home.bookTitle" var="bookTitle"/>
                <h2>${bookTitle}</h2>

                <fmt:message bundle="${loc}" key="order.form.beds.label" var="form_BedsLabel"/>
                <label>${form_BedsLabel}</label>
                <input type="number" name="beds" class="form-control">

                <fmt:message bundle="${loc}" key="order.form.class.label" var="form_classLabel"/>
                <label>${form_classLabel}</label>
                <input type="number" name="stars" class="form-control">

                <fmt:message bundle="${loc}" key="order.form.check_in.label" var="form_checkInLabel"/>
                <label>${form_checkInLabel}</label>
                <input type="date" name="checkIn" class="form-control">

                <fmt:message bundle="${loc}" key="order.form.check_out.label" var="form_checkOutLabel"/>
                <label>${form_checkOutLabel}</label>
                <input type="date" name="checkOut" class="form-control">

                <br/>
                <fmt:message bundle="${loc}" key="button.order_page" var="btn_orderPage"/>
                <button type="submit" class="btn btn-success">${btn_orderPage}</button>
            </form>
        </div>
    </c:if>
</div>
<select onchange="switchLang(this)" id="test">
    <c:choose>
        <c:when test="${sessionScope.lang == 'en'}">
            <option value="en" selected="selected">EN</option>
        </c:when>
        <c:otherwise>
            <option value="en">EN</option>
        </c:otherwise>
    </c:choose>
    <c:choose>
        <c:when test="${sessionScope.lang == 'ru'}">
            <option value="ru" selected="selected">RU</option>
        </c:when>
        <c:otherwise>
            <option value="ru">RU</option>
        </c:otherwise>
    </c:choose>
</select>
<script src="http://code.jquery.com/jquery-3.2.1.min.js"
        integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
        crossorigin="anonymous">
</script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/custom.js"></script>
</body>
</html>