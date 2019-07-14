<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Олег Демура
  Date: 06.07.2019
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<section>
    <h3><a href="index.jsp">Home</a></h3>
    <hr/>
    <h2>Restaurants</h2>
    <hr/>
    <a href="meals?action=create">Add Meal</a>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Name</th>
            <th>Address</th>
            <th>Vote</th>
        </tr>
        </thead>
        <c:forEach items="${restaurant}" var="restaurant">
            <tr>
                <td>${restaurant.name}</td>
                <td>${restaurant.address}</td>
                <td><a href="restaurants?action=voting&id=${restaurant.id}">Vote</a></td>
            </tr>
        </c:forEach>
    </table>
</section>

</body>
</html>
