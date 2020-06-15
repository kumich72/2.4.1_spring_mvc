<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <title>Edit user</title>
</head>
<body>
<h3>Edit user</h3>
<form action="edit" method="POST">
    <table border="1" cellspacing="0" cellpadding="2">
        <tr>
            <td>Id</td>
            <td>${user.id}</td>
        </tr>
        <tr>
            <td>Name</td>
            <td> ${user.name}</td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="text" id="email" name="email" value="${user.email}" /></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="text" id="password" name="password" value="${user.password}" /></td>
        </tr>
        <tr>
            <td>Role</td>
            <td>${user.role}</td>
        </tr>
    </table>
    <input type="hidden" id="id" name ="id" value="${user.id}">
    <button type="submit">Save user</button>
</form>
</body>
</html>