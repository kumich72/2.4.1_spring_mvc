<%@ page import="java.util.List" %>
<%@ page import="web.model.User " %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        Information about user
    </title>
</head>

<body>
<h1>
    <%
        User user = (User) request.getAttribute("user");
        if (user != null) {
    %>

        <table border="1">
            <tbody>
            <tr>
                <td>User ID: </td>
                <td><%=user.getId() %></td>
            </tr>
            <tr>
                <td>User Name: </td>
                <td><%=user.getName() %> </td>
            </tr>
            <tr>
                <td>User Email: </td>
                <td><%=user.getEmail() %></td>
            </tr>
            <tr>
                <td>User Password: </td>
                <td><%=user.getPassword() %></td>
            </tr>
            <tr>
                <td>User Role: </td>
                <td><%=user.getAuthorities().toString() %></td>
            </tr>
            </tbody>
        </table>
        <br>
        <% if (user.isAdmin()){%>
        <form action="/admin" method="POST">
            <input type="hidden" id="id" name ="id" value="<%=user.getId() %>">
            <input type="hidden" id="name" name ="name" value="<%=user.getName() %>">
            <input type="hidden" id="password" name ="password" value="<%=user.getPassword() %>">
            <button type="submit">Перейти на список пользователей</button>
        </form>
        <%}%>
    <%} else {%>
    Error! This user is null! <br>
    <%}%>
</h1>
</body>
</html>