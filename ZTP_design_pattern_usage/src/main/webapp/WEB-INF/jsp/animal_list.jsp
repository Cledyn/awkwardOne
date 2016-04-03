<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
<h1><spring:message code="animal.list"/></h1>
<ul>
    <%--@elvariable id="animals" type="java.util.List"--%>
    <c:forEach items="${animals}" var="animal">
        <li>
            <c:out value="${animal.getId()}"/>
            <c:out value="${animal.getNick()}"/>
            <c:out value="${animal.getSpieces()}"/>
            <c:out value="${animal.toString()}"/>
            <a href="<spring:url value="/animal_update.html?id=${animal.getId()}" />"><spring:message code="animal.update"/></a>
            <a href="<spring:url value="/animal_delete.html?id=${animal.getId()}"/>"><spring:message code="animal.delete"/></a>
            <a href="<spring:url value="/animal_show.html?id=${animal.getId()}" />"><spring:message code="animal.show"/></a>
        </li>
    </c:forEach>
</ul>

<a href="<spring:url value="/animal_create.html" />"><spring:message code="animal.create"/></a>
<a href="<spring:url value="/animal_list.html" />"><spring:message code="animal.list"/></a>
<a href="<spring:url value="/user_list.html" />"><spring:message code="user.list"/></a>
<a href="<spring:url value="/user_create.html" />"><spring:message code="user.create"/></a>
</body>
</html>
