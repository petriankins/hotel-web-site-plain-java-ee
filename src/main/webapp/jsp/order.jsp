<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 01.08.2017
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order Page</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <script src="../js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <form style="width: 400px" action="/order" method="post">
        <h2>Book!</h2>
        <label>How many beds?</label>
        <input type="number" name="beds" class="form-control">
        <label>How many stars?</label>
        <input type="number" name="stars" class="form-control">
        <label>Check-in date</label>
        <input type="date" name="checkIn" class="form-control">
        <label>Check-out date</label>
        <input type="date" name="checkOut" class="form-control">
        <br>
        <button type="submit" class="btn btn-primary btn-md">Reserve!</button>
    </form>
</div>

</body>
</html>
