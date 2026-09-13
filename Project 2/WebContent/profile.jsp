<!DOCTYPE html>
<html>

<head>
    <title>Profile</title>
</head>

<body>

<%
    String name = (String) session.getAttribute("name");
    String email = (String) session.getAttribute("email");
%>

    <h2>Welcome <%= name %></h2>

    <p>Email: <%= email %></p>

    <p>Login successful!</p>

</body>

</html>