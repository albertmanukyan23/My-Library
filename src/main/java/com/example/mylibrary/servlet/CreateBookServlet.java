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

@WebServlet("/createBook")

public class CreateBookServlet extends HttpServlet {
    private static  final AuthorManager AUTHOR_MANAGER = new AuthorManager();
    private static final BookManager BOOK_MANAGER = new BookManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Author> authors = AUTHOR_MANAGER.getAll();
        req.setAttribute("authors", authors);
        req.getRequestDispatcher("WEB-INF/createBook.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String title =  req.getParameter("title");
        String description =  req.getParameter("description");
        int authorId = Integer.parseInt(req.getParameter("author"));
        Author author = AUTHOR_MANAGER.getById(authorId);
        Book book = Book.builder().title(title)
                .description(description)
                .price(Integer.parseInt(req.getParameter("price")))
                .author(author)
                .build();
        BOOK_MANAGER.save(book);
        resp.sendRedirect("/books");
    }
}
