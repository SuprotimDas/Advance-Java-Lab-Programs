<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Update Employee</title>
    <style>
        body{ background-color: rgb(22,23,23); font-family: Arial, sans-serif; }
        .box{ width: 420px; margin: 60px auto; background: #fdfdfd; padding: 25px 30px; border-radius: 8px; }
        label{ display:block; margin-top: 12px; font-weight: bold; }
        input{ width: 100%; padding: 8px; margin-top: 4px; box-sizing: border-box; }
        button{ margin-top: 20px; width: 100%; padding: 10px; background: rgb(17,9,239); color:white; border:none; border-radius:6px; cursor:pointer; }
        a{ color: white; }
    </style>
</head>
<body>
    <div class="box">
        <h2>Update Employee</h2>
        <% if (request.getAttribute("employee") == null) { %>
            <p style="color:red;">Employee not found.</p>
        <% } else {
            com.ems.model.Employee emp = (com.ems.model.Employee) request.getAttribute("employee");
        %>
        <form action="UpdateEmployeeServlet" method="post">
            <input type="hidden" name="id" value="<%= emp.getId() %>">
            <label>Name</label>
            <input type="text" name="name" value="<%= emp.getName() %>" required>
            <label>Email</label>
            <input type="email" name="email" value="<%= emp.getEmail() %>" required>
            <label>Department</label>
            <input type="text" name="department" value="<%= emp.getDepartment() %>" required>
            <label>Salary</label>
            <input type="number" step="0.01" name="salary" value="<%= emp.getSalary() %>" required>
            <button type="submit">Save Changes</button>
        </form>
        <% } %>
    </div>
    <center><a href="index.html">&larr; Back to Home</a></center>
</body>
</html>
