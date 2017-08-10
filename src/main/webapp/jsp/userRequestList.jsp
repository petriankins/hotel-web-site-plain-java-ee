<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="localization.locale" var="loc"/>
<fmt:message bundle="${loc}" key="date_pattern" var="datePattern"/>
<div class="container">
    <fmt:message bundle="${loc}" key="home.my_requests.tablix.title" var="tablixTitle"/>
    <h1>${tablixTitle}</h1>
    <table class="table table-striped">
        <tr>
            <th>#</th>
            <fmt:message bundle="${loc}" key="request.tablix.beds" var="requestBeds"/>
            <th>${requestBeds}</th>
            <fmt:message bundle="${loc}" key="request.tablix.class" var="requestClass"/>
            <th>${requestClass}</th>
            <fmt:message bundle="${loc}" key="request.tablix.date_from" var="requestCheckIn"/>
            <th>${requestCheckIn}</th>
            <fmt:message bundle="${loc}" key="request.tablix.date_to" var="requestCheckOut"/>
            <th>${requestCheckOut}</th>
        </tr>
        <c:forEach var="request" items="${userRequests}">
            <tr>
                <td><a href="/request?num=${request.number}&action=view">${request.number}</a></td>
                <td>${request.beds}</td>
                <td>${request.classID}</td>
                <td><fmt:formatDate pattern="${datePattern}" value="${request.dateFrom}"/></td>
                <td><fmt:formatDate pattern="${datePattern}" value="${request.dateTo}"/></td>
            </tr>
        </c:forEach>
    </table>

</div>