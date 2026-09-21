<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<title>Registration Page</title>
</head>
<body>

<h2>Registration Page</h2>

<% if (request.getAttribute("error") != null) { %>
    <p><b><%= request.getAttribute("error") %></b></p>
<% } %>

<form action="RegisterServlet" method="post">
<table>
<tr><td>Name</td><td><input type="text" name="name" required></td></tr>
<tr><td>Enroll ID</td><td><input type="text" name="enrollId" required></td></tr>
<tr><td>Batch</td><td><input type="text" name="batch" required></td></tr>
<tr><td>DOB</td><td><input type="date" name="dob" required></td></tr>
<tr><td>Official Email</td><td><input type="email" name="officialEmail" required></td></tr>
<tr><td>Address</td><td><input type="text" name="address" required></td></tr>
<tr><td>Mobile No</td><td><input type="text" name="mobileNo" required></td></tr>
<tr><td>User ID</td><td><input type="text" name="userId" required></td></tr>
<tr><td>Password</td><td><input type="password" name="password" required></td></tr>
<tr><td colspan="2" align="center"><input type="submit" value="Sign Up"></td></tr>
</table>
</form>

<p><a href="index.html">Back to Home</a></p>

</body>
</html>
