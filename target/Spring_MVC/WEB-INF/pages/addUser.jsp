<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <title>Add new user</title>
</head>
<body>
<h3>Add new user</h3>
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
                <select name="role">
                <option selected value="user">user</option>
                <option value="admin">admin</option>
                </select>
            </td>
        </tr>
    </table>
    <button type="submit">Create user</button>
</form>
</body>
</html>