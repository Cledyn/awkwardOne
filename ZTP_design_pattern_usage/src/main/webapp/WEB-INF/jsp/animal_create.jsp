<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h1><spring:message code="animal.create"/></h1>
<a href="<spring:url value="/animal_list.html" />"><spring:message code="animal.list"/></a>
<c:set var="owners" value="${possibleOwners}" />
<form:form method="POST" action="/animal_create.html" modelAttribute="form">
    <form:errors path="" element="div"/>
    <div>
        <form:label path="id"><spring:message code="animal.id"/></form:label>
        <form:input path="id"/>
        <form:errors path="id"/>
    </div>
    <div>
        <form:label path="nick"><spring:message code="animal.nick"/></form:label>
        <form:input path="nick"/>
    </div>
    <div>
        <form:label path="age"><spring:message code="animal.age"/></form:label>
        <form:input path="age"/>
    </div>
    <div>
        <form:label path="spieces"><spring:message code="animal.spieces"/></form:label>
        <form:input path="spieces"/>
    </div>
    <div>
        <c:choose>
                    <c:when test="${not empty owners}">
                        <select name="selectOwner">
                            <c:forEach items="${owners}" var="sampleOwner">
                                <option value="${sampleOwner.getId()}"><c:out value="${sampleOwner.toString()}" /></option>

                            </c:forEach>
                        </select>
                    </c:when>
                    <c:otherwise>
                        <p>No owner present.</p>
                    </c:otherwise>
                </c:choose>
    </div>
    <div>
        <input type="submit"/>
    </div>
</form:form>
</body>
</html>
