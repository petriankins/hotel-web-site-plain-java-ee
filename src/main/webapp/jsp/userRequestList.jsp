<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="localization.locale" var="loc"/>
<fmt:message bundle="${loc}" key="date_pattern" var="datePattern"/>
<div class="container">
    <%--<fmt:message bundle="${loc}" key="unhandledRequests.table" var="unhandledReqTable"/>--%>
    <h1>My requests</h1>
    <table class="table table-striped">
        <tr>
            <th>#</th>
            <th>Beds</th>
            <th>Class</th>
            <th>From</th>
            <th>To</th>
        </tr>
        <c:forEach var="request" items="${userRequests}">
            <tr>
                <td>${request.number}</td>
                <td>${request.beds}</td>
                <td>${request.classID}</td>
                <td><fmt:formatDate pattern="${datePattern}" value="${request.dateFrom}"/></td>
                <td><fmt:formatDate pattern="${datePattern}" value="${request.dateTo}"/></td>
            </tr>
        </c:forEach>
    </table>

</div>