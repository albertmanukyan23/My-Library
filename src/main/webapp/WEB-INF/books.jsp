<%@ page import="com.example.mylibrary.model.Book" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Albert
  Date: 27/04/2023
  Time: 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Books</title>
</head>
<% List<Book> books = (List<Book>) request.getAttribute("allBooks");%>
<style>
  table, th, td {
    border: 1px solid;
  }
</style>

<body>
<a style="margin-left: 50px" href="/"> Back </a><br>
<h2>Books</h2>

<table>
  <tr>
    <th>id</th>
    <th>Title</th>
    <th>Description</th>
    <th>Price</th>
    <th>Author_id</th>
    <th>Delete/Update</th>

  </tr>
  <% if (books != null || !books.isEmpty()) {%>
  <% for (Book book : books) {%>
  <tr>
    <td><%= book.getId()%>
    </td>
    <td><%= book.getTitle()%>
    </td>
    <td><%=book.getDescription()%>
    </td>
    </td>
    <td><%=book.getPrice()%>
    </td>
    <td><%=book.getAuthor().getId()%>
    </td>
    <td><a href="/deleteBook?id=<%= book.getId()%>">Delete </a>/ <a
            href="/updateBook?id=<%=book.getId()%>">Update </a></td>
    </td>
  </tr>
  <% }
  }%>


</table>

</body>
</html>
