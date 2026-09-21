<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<title>Login Page</title>
</head>
<body>

<h2>Login Page</h2>

<% if ("registered".equals(request.getParameter("status"))) { %>
    <p>Registration successful. Please log in.</p>
<% } %>
<% if (request.getAttribute("error") != null) { %>
    <p><b><%= request.getAttribute("error") %></b></p>
<% } %>

<form action="LoginServlet" method="post">
<table>
<tr><td>User ID</td><td><input type="text" name="userId" required></td></tr>
<tr><td>Password</td><td><input type="password" name="password" required></td></tr>
<tr><td colspan="2" align="center"><input type="submit" value="Submit"></td></tr>
</table>
</form>

<p><a href="index.html">Back to Home</a></p>

</body>
</html>
