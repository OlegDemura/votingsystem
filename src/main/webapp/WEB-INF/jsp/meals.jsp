<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Олег Демура
  Date: 06.07.2019
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="fragments/headTag.jsp"/>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<h2>Название</h2>
<p>${restaurant.name}</p>
<h2>Адрес</h2>
<p>${restaurant.address}</p>

<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th><spring:message code="meal.description"/></th>
        <th><spring:message code="meal.price"/></th>
    </tr>
    </thead>
    <c:forEach items="${meals}" var="meal">
        <tr>
            <td>${meal.description}</td>
            <td>${meal.price}</td>
        </tr>
    </c:forEach>
</table>
<a href="votes/create?restaurantId=${restaurant.id}"><spring:message code="restaurant.vote"/></a>
<a href="votes/delete?id=0&restaurantId=${restaurant.id}"><spring:message code="restaurant.delete_vote"/></a>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
