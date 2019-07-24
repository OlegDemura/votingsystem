<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
    <title><spring:message code="restaurant.title"/></title>
    <jsp:include page="fragments/headTag.jsp"/>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
    <h2><spring:message code="restaurant.title"/></h2>
    <hr/>
    <a href="restaurants/create"><spring:message code="restaurant.create"/></a>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th><spring:message code="restaurant.name"/></th>
            <th><spring:message code="restaurant.address"/></th>
            <th><spring:message code="restaurant.view"/></th>
            <th><spring:message code="restaurant.vote"/></th>
            <th><spring:message code="restaurant.delete_vote"/></th>
        </tr>
        </thead>
        <c:forEach items="${restaurants}" var="restaurant">
            <tr>
                <td>${restaurant.name}</td>
                <td>${restaurant.address}</td>
                <td><a href="restaurants/view?restaurantId=${restaurant.id}"><spring:message code="restaurant.view"/></a></td>
                <td><a href="votes/create?restaurantId=${restaurant.id}"><spring:message code="restaurant.vote"/></a></td>
                <td><a href="votes/delete?id=0&restaurantId=${restaurant.id}"><spring:message code="restaurant.delete_vote"/></a></td>
            </tr>
        </c:forEach>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
