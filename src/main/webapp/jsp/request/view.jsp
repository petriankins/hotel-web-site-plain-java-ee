<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ht" uri="/WEB-INF/headerTag.tld" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="localization.locale" var="loc"/>
    <fmt:message bundle="${loc}" key="date_pattern" var="datePattern"/>
    <fmt:message bundle="${loc}" key="page_title.request.view" var="pageTitle"/>
    <title>${pageTitle}</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/custom.css">
</head>
<body>
<ht:HeaderTag/>
<div class="container container-main">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">
                <div class="row">
                    <fmt:message bundle="${loc}" key="request.view.tablix.title" var="tablixTitle"/>
                    <div class="col-lg-6">${tablixTitle} №${request.number}</div>
                    <fmt:message bundle="${loc}" key="request.tablix.status" var="requestStatusText"/>
                    <%--TODO take actual status based on database info!--%>
                    <c:choose>
                        <c:when test="${requestStatus == '1'}">
                            <fmt:message bundle="${loc}" key="request.view.status.new" var="requestStatusLoc"/>
                        </c:when>
                        <c:when test="${requestStatus == '2'}">
                            <fmt:message bundle="${loc}" key="request.view.status.billed" var="requestStatusLoc"/>
                        </c:when>
                        <c:when test="${requestStatus == '3'}">
                            <fmt:message bundle="${loc}" key="request.view.status.paid" var="requestStatusLoc"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="requestStatus" value=""/>
                        </c:otherwise>
                    </c:choose>
                    <div class="col-lg-6 text-right">${requestStatusText}: ${requestStatusLoc}</div>
                </div>
            </h3>
        </div>
        <div class="panel-body">
            <div class="row">
                <fmt:message bundle="${loc}" key="request.tablix.beds" var="requestBeds"/>
                <div class="col-lg-3">${requestBeds}:</div>
                <div class="col-lg-3">${request.beds}</div>
                <fmt:message bundle="${loc}" key="request.tablix.class" var="requestClass"/>
                <div class="col-lg-3">${requestClass}:</div>
                <div class="col-lg-3">${request.classID}</div>
            </div>
            <div class="row">
                <fmt:message bundle="${loc}" key="request.tablix.date_from" var="requestCheckIn"/>
                <div class="col-lg-3">${requestCheckIn}:</div>
                <div class="col-lg-3"><fmt:formatDate pattern="${datePattern}" value="${request.dateFrom}"/></div>
                <fmt:message bundle="${loc}" key="request.tablix.date_to" var="requestCheckOut"/>
                <div class="col-lg-3">${requestCheckOut}:</div>
                <div class="col-lg-3"><fmt:formatDate pattern="${datePattern}" value="${request.dateTo}"/></div>
            </div>
            <div class="row">
                <fmt:message bundle="${loc}" key="request.view.tablix.comments" var="requestComments"/>
                <div class="col-lg-12">${requestComments}</div>
            </div>
            <div class="row">
                <div class="col-lg-12">${request.comments}</div>
            </div>
        </div>
        <div class="panel-footer text-right">
            <c:if test="${requestStatus != '1'}">
                <fmt:message bundle="${loc}" key="button.bill" var="btnBill"/>
                <a href="/bill?request=${request.number}" class="btn btn-primary btn-md">${btnBill}</a>
            </c:if>
            <c:if test="${requestStatus == '1'}">
                <fmt:message bundle="${loc}" key="button.edit" var="btnEdit"/>
            </c:if>
            <c:if test="${requestStatus != '3'}">
                <fmt:message bundle="${loc}" key="button.delete" var="btnDelete"/>
                <a href="/request?num=${request.number}&action=delete" class="btn btn-danger btn-md">${btnDelete}</a>
            </c:if>
            <fmt:message bundle="${loc}" key="button.back" var="btnBack"/>
            <a href="#" class="btn btn-primary btn-md" onclick="window.history.back()">${btnBack}</a>
        </div>
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
