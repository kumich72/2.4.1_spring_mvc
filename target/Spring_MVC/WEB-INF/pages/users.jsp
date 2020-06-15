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
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>${user.password}</td>
            <td>${user.role}</td>
            <td><form action="delete" method="POST">
                <button type="submit">Delete</button>
                <input type="hidden" id="id" name="id" value="${user.id}">
            </form></td>
            <td><form action="editing" method="POST">
                <button type="submit">Edit</button>
                <input type="hidden" id="edit_id"  name="edit_id" value="${user.id}">
            </form></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>