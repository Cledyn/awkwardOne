<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h1><spring:message code="user.update"/></h1>
<a href="<spring:url value="/user_list.html" />"><spring:message code="user.list"/></a>
<c:set var="user" value="${userToUpdate}" />
<c:set var="Name" value="${user.getId()}"/>
<form:form method="POST" action="/edit.html" modelAttribute="form">
    <table>
        <tr>
            <td><form:label path="id">User ID:</form:label></td>
            <td><form:input path="id" value="${user.getId()}" readonly="true"/></td>
        </tr>
        <tr>
            <td><form:label path="password">Password:</form:label></td>
            <td><form:input path="password" value="${user.getPassword()}"/></td>
        </tr>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form:form>
<a href="<spring:url value="/user_list.html" />"><spring:message code="user.list"/></a>
</body>
</html>
