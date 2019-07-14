<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Олег Демура
  Date: 14.07.2019
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<br>
<form method="post" action="users">
    <spring:message code="app.login"/>
    <select name="userId">
        <option value="100000" selected>User</option>
        <option value="100001">Admin</option>
    </select>
<button type="submit"><spring:message code="common.select"/> </button>
</form>
</body>
</html>
