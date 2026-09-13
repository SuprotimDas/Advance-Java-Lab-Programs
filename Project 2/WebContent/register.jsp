<!DOCTYPE html>
<html>

<head>
    <title>Registration</title>
</head>

<body>

    <h2>Registration Form</h2>

    <form action="Register" method="post">

        <label>Name:</label>
        <input type="text" name="name" required>
        <br><br>

        <label>Email:</label>
        <input type="email" name="email" required>
        <br><br>

        <label>Password:</label>
        <input type="password" name="password" required>
        <br><br>

        <input type="submit" value="Register">

    </form>

    <br>

    <a href="login.jsp">Already registered? Login</a>

</body>

</html>