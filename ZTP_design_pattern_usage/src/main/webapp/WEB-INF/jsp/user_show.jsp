<%--<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>--%>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Show user</title>
</head>
<body>
<h1>User</h1>


<c:set var="user" value="${chosenUser}"/>
<table align="left" border="1">
    <tr>
        <th>ID</th>
        <th>Password</th>
        <th>Animal limit</th>
        <th>Actual number of animals</th>
        <th>Animals</th>
    </tr>
    <tr>
        <td><c:out value="${user.getId()}"/></td>
        <td><c:out value="${user.getPassword()}"/></td>
        <td><c:out value="${user.getMaxAnimalNo()+1}"/></td>
        <td><c:out value="${user.getActualAnimalNo()}"/></td>
        <td>
            <c:forEach items="${animals}" var="animal">
                <c:out value="${animal.getId()}"/><br>
            </c:forEach>
        </td>
    </tr>
</table>
<a href="<spring:url value="/user_list.html" />"><spring:message code="user.list"/></a>
</body>

</html>
