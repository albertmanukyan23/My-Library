package com.example.mylibrary.servlet;


import com.example.mylibrary.manager.AuthorManager;
import com.example.mylibrary.manager.BookManager;
import com.example.mylibrary.model.Author;
import com.example.mylibrary.model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/updateBook")
public class UpdateBookServlet extends HttpServlet {
    private static final BookManager BOOK_MANAGER = new BookManager();

    private static final AuthorManager AUTHOR_MANAGER = new AuthorManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Book book = BOOK_MANAGER.getById(id);
        List<Author> authors = AUTHOR_MANAGER.getAll();
        req.setAttribute("book", book);
        req.setAttribute("authors", authors);
        req.getRequestDispatcher("WEB-INF/BookUpdate.jsp").forward(req,resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String title =  req.getParameter("title");
        String description =  req.getParameter("description");
        int price =  Integer.parseInt(req.getParameter("price"));
        int authorId = Integer.parseInt(req.getParameter("author"));
        Author author = AUTHOR_MANAGER.getById(authorId);
        Book book = new Book(id,title,description,price,author);
        BOOK_MANAGER.update(book);
        resp.sendRedirect("/books");
    }
}
