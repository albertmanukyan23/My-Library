package com.example.mylibrary.servlet;


import com.example.mylibrary.manager.BookManager;
import com.example.mylibrary.model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/books")
public class BooksServlet extends HttpServlet {
    private static final BookManager BOOK_MANAGER = new BookManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> all = BOOK_MANAGER.getAll();
        req.setAttribute("allBooks", all);
        req.getRequestDispatcher("WEB-INF/books.jsp").forward(req,resp);
    }
}
