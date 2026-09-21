<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Select Employee</title>
    <style>
        body{ background-color: rgb(22,23,23); font-family: Arial, sans-serif; color: white; }
        .box{ width: 700px; margin: 40px auto; background: #fdfdfd; padding: 25px 30px; border-radius: 8px; color: #222; }
        table{ width: 100%; border-collapse: collapse; margin-top: 15px; }
        th, td{ border: 1px solid #ccc; padding: 8px 10px; text-align: left; }
        th{ background: rgb(17,9,239); color: white; }
        a.action{ color: rgb(17,9,239); text-decoration: none; font-weight: bold; }
        a.home{ color: white; }
    </style>
</head>
<body>
    <center>
    <div class="box">
        <h2>Select an Employee</h2>
        <p>Choose which record to <c:choose><c:when test="${mode == 'delete'}">delete</c:when><c:otherwise>update</c:otherwise></c:choose>:</p>
        <table>
            <tr><th>ID</th><th>Name</th><th>Department</th><th>Action</th></tr>
            <c:forEach var="emp" items="${employees}">
                <tr>
                    <td>${emp.id}</td>
                    <td>${emp.name}</td>
                    <td>${emp.department}</td>
                    <td>
                        <c:choose>
                            <c:when test="${mode == 'delete'}">
                                <a class="action" href="DeleteEmployeeServlet?id=${emp.id}"
                                   onclick="return confirm('Delete this employee?');">Delete</a>
                            </c:when>
                            <c:otherwise>
                                <a class="action" href="UpdateEmployeeServlet?id=${emp.id}">Edit</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <a class="home" href="index.html">&larr; Back to Home</a>
    </center>
</body>
</html>
