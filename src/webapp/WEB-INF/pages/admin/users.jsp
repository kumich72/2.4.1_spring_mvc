<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <title>User's list</title>
</head>
<body>
<h3>User's list</h3>

<form action="addUser" method="GET">
    <button type="submit">Add new user</button>
</form>

<table border="1" cellspacing="0" cellpadding="2">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Email</th>
        <th>Password</th>
        <th>Role</th>
        <th>Delete</th>
        <th>Edit</th>
    </tr>
    <c:forEach items="${userRoles}" var="userRole">
        <tr>
            <td>${userRole.user.id}</td>
            <td>${userRole.user.name}</td>
            <td>${userRole.user.email}</td>
            <td>${userRole.user.password}</td>
            <td>
                <c:forEach items="${userRole.roles}" var="role">
                    <h1>${role.name}</h1>
                </c:forEach>
            </td>
            <td><form action="delete" method="POST">
                <button type="submit">Delete</button>
                <input type="hidden" id="id" name="id" value="${userRole.user.id}">
            </form></td>
            <td><form action="editing" method="POST">
                <button type="submit">Edit</button>
                <input type="hidden" id="edit_id"  name="edit_id" value="${userRole.user.id}">
            </form></td>
        </tr>
    </c:forEach>
</table>
<a href="<c:url value="/logout" />">Logout</a>
</body>
</html>