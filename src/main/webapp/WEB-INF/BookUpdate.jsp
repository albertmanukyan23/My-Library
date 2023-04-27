<%@ page import="com.example.mylibrary.model.Book" %>
<%@ page import="com.example.mylibrary.model.Author" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Albert
  Date: 27/04/2023
  Time: 13:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book Edit</title>
</head>
<% Book book = (Book) request.getAttribute("book");%>
<% List<Author> authors = (List<Author>) request.getAttribute("authors");%>
<body>
<a href="/books"> Back </a>

<h2>Edit Book</h2>
<form action="/updateBook" method="post">
  <input name="id" type="hidden" value="<%=book.getId()%>"><br>
  Title: <input name="title" type="text" value="<%=book.getTitle()%>"><br>
  Description: <input name="description" type="text" value="<%=book.getDescription()%>"><br>
  Price: <input name="price" type="text" value="<%=book.getPrice()%>"><br>
  Author:
  <select name="author">
    <% for (Author author : authors) { %>
    <option value="<%=author.getId()%>" ><%= author.getName() %></option>
    <% }%>
  </select>
  <input type="submit" value="Update">
</form>

</body>
</html>
