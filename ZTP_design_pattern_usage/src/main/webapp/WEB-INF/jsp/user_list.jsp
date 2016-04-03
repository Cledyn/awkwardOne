<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
<h1><spring:message code="user.list"/></h1>
<ul>
    <%--@elvariable id="users" type="java.util.List"--%>
    <c:forEach items="${users}" var="user">
        <li>
            <c:out value="${user.getId()}"/>
            <c:out value="${user.getPassword()}"/>
            <c:out value="${user.toString()}"/>
            <a href="<spring:url value="/update.html?id=${user.getId()}" />"><spring:message code="user.update"/></a>
            <a href="<spring:url value="/delete.html?id=${user.getId()}"/>"><spring:message code="user.delete"/></a>
            <a href="<spring:url value="/show.html?id=${user.getId()}" />"><spring:message code="user.show"/></a>
        </li>
    </c:forEach>
</ul>

<a href="<spring:url value="/user_create.html" />"><spring:message code="user.create"/></a>
<a href="<spring:url value="/animal_list.html" />"><spring:message code="animal.list"/></a>
</body>
</html>