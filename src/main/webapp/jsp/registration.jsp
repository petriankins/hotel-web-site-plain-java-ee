<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<div class="container">
    <c:if test="${not empty message}">
        <div class="alert alert-danger">
                ${message}
        </div>
    </c:if>
    <form action="/registration" method="post">
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
            <input type="password" name="password" class="form-control" placeholder="Password"/>
        </div>
        <div class="form-group">
            <label>Password confirm:</label>
            <input type="password" name="passwordConfirm" class="form-control" placeholder="Password"/>
        </div>
        <button type="button" class="btn btn-success btn-md" onclick="checkForm(this.parentNode);">Sign up</button>
        <button type="submit" class="btn btn-success btn-md" id="submitBtn" style="display: none;">Sign up</button>
        <a class="btn btn-primary btn-md" href="/" role="button">Back</a>
    </form>
</div>
<script src="http://code.jquery.com/jquery-3.2.1.min.js"
        integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
        crossorigin="anonymous">
</script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/custom.js"></script>
</body>
</html>
