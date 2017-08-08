<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ht" uri="/WEB-INF/headerTag.tld" %>
<html>
<head>
    <title>Home Page</title>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="localization.locale" var="loc"/>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/custom.css">
</head>
<body>
<ht:HeaderTag />
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
<script src="http://code.jquery.com/jquery-3.2.1.min.js"
        integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
        crossorigin="anonymous">
</script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/custom.js"></script>
</body>
</html>