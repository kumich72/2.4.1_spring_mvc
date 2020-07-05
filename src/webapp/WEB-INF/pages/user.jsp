<%@ page import="java.util.List" %>
<%@ page import="web.model.User" %>
<%@ page import="web.model.Role" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <title>
        Information about user
    </title>
</head>

<body>
<h1>
    <table border="1">
        <tbody>
        <tr>
            <td>User ID:</td>
            <td>${user.id}</td>
        </tr>
        <tr>
            <td>User Name:</td>
            <td> ${user.name} </td>
        </tr>
        <tr>
            <td>User Email:</td>
            <td>${user.email}</td>
        </tr>
        <tr>
            <td>User Password:</td>
            <td>${user.password}</td>
        </tr>
        <tr>
            <td>User Role:</td>
            <td>
                <c:forEach items="${roleList}" var="role">
                    <h1>${role.name}</h1>
                </c:forEach>
            </td>
        </tr>
        </tbody>
    </table>
    <br>
</h1>
<a href="<c:url value="/logout" />">Logout</a>
</body>
</html>