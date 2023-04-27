
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Author</title>
</head>
<body>
<h2>Add Author</h2>
<form action="/createAuthor" method="post">
    Name: <input name="name" type="text" ><br><br>
    Surname: <input name="surname" type="text"><br><br>
    email: <input name="email" type="text"><br><br>
    age: <input name="age" type="text"><br><br>
    <input type="submit" value="Add"><br><br>
</form>
</body>
</html>
