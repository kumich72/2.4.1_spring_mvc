<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <title>Add new user</title>
</head>
<body>
<h3>Add new user</h3>
<%--${pageContext}--%>
<form action="add" method="POST">
    <table border="1" cellspacing="0" cellpadding="2">
        <tr>
            <td>Name</td>
            <td><input type="text" id="name" name="name"  /></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="text" id="email" name="email"/></td>
        </tr>
        <tr>
            <td>Password</td>
            <td>
                <input type="text" id="password" name="password"/></td>
        </tr>
        <tr>
            <td>Role</td>
            <td>
                <c:forEach var="role" items="${roles}">
                    <label class="checkbox"><input type="checkbox"  value="${role.name}" name="roles">${role.name}</label>
                </c:forEach>
            </td>
        </tr>
    </table>
    <button type="submit">Create user</button>
</form>
<a href="<c:url value="/logout" />">Logout</a>
</body>
</html>