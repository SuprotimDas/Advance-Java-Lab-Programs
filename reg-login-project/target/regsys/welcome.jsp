<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<title>Welcome</title>
</head>
<body>

<%
    com.regsys.model.User user = (com.regsys.model.User) request.getAttribute("loggedInUser");
%>

<h2>Login Successful</h2>
<p>Welcome, <%= user.getName() %> (User ID: <%= user.getUserId() %>)</p>

<p>
<a href="ViewUsersServlet">View Registered Users</a> |
<a href="index.html">Back to Home</a>
</p>

</body>
</html>
