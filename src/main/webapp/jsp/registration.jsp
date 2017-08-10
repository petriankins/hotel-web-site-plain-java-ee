<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="h1" uri="/WEB-INF/headerTag.tld" %>

<html>
<head>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="localization.locale" var="loc"/>
    <fmt:message bundle="${loc}" key="page_title.registration" var="pageTitle"/>
    <title>${pageTitle}</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/custom.css">
</head>
<body>
<h1:HeaderTag/>
<div class="jumbotron">
    <div class="container">
        <form action="/registration" method="post">
            <div class="form-group">
                <fmt:message bundle="${loc}" key="first.name" var="firstName"/>
                <label>${firstName}</label>
                <input type="text" name="name" class="form-control" placeholder="${firstName}">
            </div>
            <div class="form-group">
                <fmt:message bundle="${loc}" key="last.name" var="lastName"/>
                <label>${lastName}</label>
                <input type="text" name="lastName" class="form-control" placeholder="${lastName}">
            </div>
            <div class="form-group">
                <fmt:message bundle="${loc}" key="email" var="email"/>
                <label>${email}</label>
                <input type="email" name="login" class="form-control" placeholder="${email}">
            </div>
            <div class="form-group">
                <fmt:message bundle="${loc}" key="login.password_label" var="pass"/>
                <label>${pass}</label>
                <input type="password" name="password" class="form-control" placeholder="${pass}"/>
            </div>
            <div class="form-group">
                <fmt:message bundle="${loc}" key="password.confirm" var="passConf"/>
                <label>${passConf}</label>
                <fmt:message bundle="${loc}" key="login.password_label" var="pass"/>
                <input type="password" name="passwordConfirm" class="form-control" placeholder="${pass}"/>
            </div>
            <fmt:message bundle="${loc}" key="button.sign_up" var="signUp"/>
            <button type="button" class="btn btn-success btn-md" onclick="checkForm(this.parentNode);">${signUp}</button>
            <button type="submit" class="btn btn-success btn-md" id="submitBtn" style="display: none;">${signUp}</button>
            <fmt:message bundle="${loc}" key="button.back" var="back"/>
            <a class="btn btn-primary btn-md" href="/" role="button">${back}</a>
        </form>
    </div>
</div>
<script src="http://code.jquery.com/jquery-3.2.1.min.js"
        integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
        crossorigin="anonymous">
</script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/custom.js"></script>
</body>
</html>
