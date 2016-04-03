<%--
  Created by IntelliJ IDEA.
  User: Sandra
  Date: 2016-02-27
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h2>List Employees</h2>
<table align="left" border="1">
    <tr>
        <th>Employee ID</th>
        <th>Employee Name</th>
        <th>Employee Age</th>
        <th>Employee Salary</th>
        <th>Employee Address</th>
        <th>Actions on Row</th>
    </tr>

    <c:forEach items="${employees}" var="employee">
        <tr>
            <td><c:out value="${employee.id}"/></td>
            <td><c:out value="${employee.name}"/></td>
            <td><c:out value="${employee.age}"/></td>
            <td><c:out value="${employee.salary}"/></td>
            <td><c:out value="${employee.address}"/></td>
            <td align="center"><a href="edit.html?id=${employee.id}">Edit</a> | <a href="delete.html?id=${employee.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<a href="<spring:url value="/user_list.html" />"><spring:message code="user.list"/></a>
</body>
</html>
