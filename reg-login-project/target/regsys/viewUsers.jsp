<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>Registered Users</title>
</head>
<body>

<h2>Registered Users</h2>

<% if (request.getAttribute("error") != null) { %>
    <p><b><%= request.getAttribute("error") %></b></p>
<% } %>

<table border="1" cellpadding="5" cellspacing="0">
<tr>
<th>ID</th><th>Name</th><th>Enroll ID</th><th>Batch</th><th>DOB</th>
<th>Official Email</th><th>Address</th><th>Mobile No</th><th>User ID</th>
</tr>
<c:forEach var="u" items="${users}">
<tr>
<td>${u.id}</td>
<td>${u.name}</td>
<td>${u.enrollId}</td>
<td>${u.batch}</td>
<td>${u.dob}</td>
<td>${u.officialEmail}</td>
<td>${u.address}</td>
<td>${u.mobileNo}</td>
<td>${u.userId}</td>
</tr>
</c:forEach>
</table>

<p><a href="index.html">Back to Home</a></p>

</body>
</html>
