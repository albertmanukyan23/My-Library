<%@ page import="com.example.mylibrary.model.Author" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Albert
  Date: 27/04/2023
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authors</title>
</head>
<% List<Author> authors = (List<Author>) request.getAttribute("allAuthors");%>
<style>
  table, th, td {
    border: 1px solid;
  }
</style>


<body>
<h2>Authors</h2><br><br>

<table>
  <tr>
    <th>id</th>
    <th>name</th>
    <th>surname</th>
    <th>email</th>
    <th>age</th>
    <th>Delete/Update</th>
  </tr>
  <% if (authors != null || !authors.isEmpty()) {%>
  <% for (Author author : authors) {%>
  <tr>
    <td><%= author.getId()%>
    </td>
    <td><%= author.getName()%>
    </td>
    <td><%=author.getSurname()%>
    </td>
    <td><%=author.getEmail()%>
    </td>
    <td><%=author.getAge()%>
    </td>
    <td><a href="/deleteAuthor?id=<%= author.getId()%>">Delete </a>/ <a
            href="/updateAuthor?id=<%=author.getId()%>">Update </a></td>
    </td>
  </tr>
  <% }
  }%>


</table>

</body>
</html>
