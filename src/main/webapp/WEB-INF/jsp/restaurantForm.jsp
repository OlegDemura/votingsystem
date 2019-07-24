<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Олег Демура
  Date: 21.07.2019
  Time: 23:40
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
<section>
    <hr>
    <jsp:useBean id="restaurant" type="ru.votingsystem.model.Restaurant" scope="request"/>
    <h2><spring:message code="${restaurant.isNew() ? 'restaurant.create' : 'restaurant.update'}"/></h2>
    <form method="post" action="restaurants">
        <input type="hidden" name="restaurantId" value="${restaurant.id}">
        <dl>
            <dt><spring:message code="restaurant.name"/></dt>
            <dd><input type="text" value="${restaurant.name}" name="name" required></dd>
        </dl>
        <dl>
            <dt><spring:message code="restaurant.address"/></dt>
            <dd><input type="text" value="${restaurant.address}" name="address" required></dd>
        </dl>
        <button type="submit"><spring:message code="common.save"/></button>
        <button onclick="window.history.back()" type="button"><spring:message code="common.cancel"/></button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
