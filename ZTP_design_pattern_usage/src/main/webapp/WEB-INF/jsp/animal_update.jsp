<%@ page import="com.pwr.izinf.domain.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h1><spring:message code="animal.update"/></h1>
<a href="<spring:url value="/animal_list.html" />"><spring:message code="animal.list"/></a>
<c:set var="animal" value="${animalToUpdate}" />
<c:set var="owners" value="${possibleOwners}" />
<form:form method="POST" action="/animal_edit.html" modelAttribute="form">
    <table>
        <tr>
            <td><form:label path="id">Animal ID:</form:label></td>
            <td><form:input path="id" value="${animal.getId()}" readonly="true"/></td>
        </tr>
        <tr>
            <td><form:label path="nick">Nick:</form:label></td>
            <td><form:input path="nick" value="${animal.getNick()}"/></td>
        </tr>
        <tr>
            <td><form:label path="spieces">Spieces:</form:label></td>
            <td><form:input path="spieces" value="${animal.getSpieces()}"/></td>
        </tr>
        <tr>
            <c:choose>
                <c:when test="${not empty owners}">
                    <%--<td><form:select commandName="User" path="owner" items="${owners}" itemLabel="${sampleOwner.toString()}" itemValue="${sampleOwner}" /></td>--%>
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
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Submit"/></td>
        </tr>
    </table>
  </form:form>
  <a href="<spring:url value="/animal_list.html" />"><spring:message code="animal.list"/></a>
</body>
</html>
