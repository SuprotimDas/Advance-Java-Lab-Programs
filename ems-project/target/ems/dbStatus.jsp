<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>DB Connection Test</title>
    <style>
        body{ background-color: rgb(22,23,23); font-family: Arial, sans-serif; color: white; text-align: center; }
        .box{ width: 500px; margin: 80px auto; background: #fdfdfd; padding: 30px; border-radius: 8px; color: #222; }
        .ok{ color: green; font-weight: bold; }
        .fail{ color: #c0392b; font-weight: bold; }
        a{ color: white; }
    </style>
</head>
<body>
    <div class="box">
        <h2>Database Connection Test</h2>
        <% if (Boolean.TRUE.equals(request.getAttribute("success"))) { %>
            <p class="ok"><%= request.getAttribute("message") %></p>
        <% } else { %>
            <p class="fail"><%= request.getAttribute("message") %></p>
        <% } %>
    </div>
    <a href="index.html">&larr; Back to Home</a>
</body>
</html>
