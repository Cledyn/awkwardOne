<%--<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>--%>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Show animal</title>
</head>
<body>
<h1>Animal</h1>


<c:set var="animal" value="${chosenAnimal}"/>
<c:set var="owner" value="${chosenAnimal.getOwner()}"/>
<table align="left" border="1">
    <tr>
        <th>ID</th>
        <th>Nick</th>
        <th>Spieces</th>
        <th>Age</th>
        <th>Owner</th>
    </tr>
    <tr>
        <td><c:out value="${animal.getId()}"/></td>
        <td><c:out value="${animal.getNick()}"/></td>
        <td><c:out value="${animal.getSpieces()}"/></td>
        <c:choose>
            <c:when test="${not empty age}">
                <td><c:out value="${animal.getAge()}"/></td>
            </c:when>
            <c:otherwise>
                <td><p>No age specified.</p></td>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${not empty owner}">
                <td><c:out value="${owner.toString()}"/></td>
            </c:when>
            <c:otherwise>
               <td><p>No owner assigned.</p></td>
            </c:otherwise>
        </c:choose>

    </tr>

</table>
<a href="<spring:url value="/animal_list.html" />"><spring:message code="animal.list"/></a>
</body>
</html>
