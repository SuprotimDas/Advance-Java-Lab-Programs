<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>All Employees</title>
    <style>
        body{ background-color: rgb(22,23,23); font-family: Arial, sans-serif; color: white; }
        .box{ width: 800px; margin: 40px auto; background: #fdfdfd; padding: 25px 30px; border-radius: 8px; color: #222; }
        table{ width: 100%; border-collapse: collapse; margin-top: 15px; }
        th, td{ border: 1px solid #ccc; padding: 8px 10px; text-align: left; }
        th{ background: rgb(17,9,239); color: white; }
        a.action{ margin-right: 10px; color: rgb(17,9,239); text-decoration: none; font-weight: bold; }
        a.action.delete{ color: #c0392b; }
        .status{ color: green; font-weight: bold; }
        a.home{ color: white; }
    </style>
</head>
<body>
    <center>
    <div class="box">
        <h2>All Employees</h2>
        <% if ("added".equals(request.getParameter("status"))) { %>
            <p class="status">Employee added successfully.</p>
        <% } else if ("updated".equals(request.getParameter("status"))) { %>
            <p class="status">Employee updated successfully.</p>
        <% } else if ("deleted".equals(request.getParameter("status"))) { %>
            <p class="status">Employee deleted successfully.</p>
        <% } %>
        <% if (request.getAttribute("error") != null) { %>
            <p style="color:red;"><%= request.getAttribute("error") %></p>
        <% } %>
        <table>
            <tr>
                <th>ID</th><th>Name</th><th>Email</th><th>Department</th><th>Salary</th><th>Actions</th>
            </tr>
            <c:forEach var="emp" items="${employees}">
                <tr>
                    <td>${emp.id}</td>
                    <td>${emp.name}</td>
                    <td>${emp.email}</td>
                    <td>${emp.department}</td>
                    <td>${emp.salary}</td>
                    <td>
                        <a class="action" href="UpdateEmployeeServlet?id=${emp.id}">Edit</a>
                        <a class="action delete" href="DeleteEmployeeServlet?id=${emp.id}"
                           onclick="return confirm('Delete this employee?');">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <a class="home" href="index.html">&larr; Back to Home</a>
    </center>
</body>
</html>
