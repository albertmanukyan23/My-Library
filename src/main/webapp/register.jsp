<%--
  Created by IntelliJ IDEA.
  User: Albert
  Date: 27/04/2023
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<%
  if(session.getAttribute("user") != null){
    response.sendRedirect("/home");
  }
%>
Register:
<form action="/register" method="post">
  Name: <input name="name" type="text"><br/>
  Surname: <input name="surname" type="text"><br/>
  Email: <input name="email" type="text"><br/>
  Password<input name="password" type="password"><br/>
  <input type="submit" value="register">
</form>
<a href="/">Back</a>

</body>
</html>
