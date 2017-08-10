<div class="container">
    <%--<fmt:message bundle="${loc}" key="unhandledRequests.table" var="unhandledReqTable"/>--%>
    <h1>My requests</h1>
    <table class="table table-striped">
        <tr>
            <th>#</th>
            <th>Beds</th>
            <th>Class</th>
        </tr>
        <c:forEach var="request" items="${unhandledRequests}">
            <tr>
                <td>123</td>
                <td>1</td>
                <td>1</td>
            </tr>
        </c:forEach>
    </table>

</div>