package com.example.mylibrary.servlet;


import com.example.mylibrary.manager.BookManager;
import com.example.mylibrary.model.Book;
import com.example.mylibrary.model.User;
import com.example.mylibrary.model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/books")
public class BooksServlet extends HttpServlet {
    private static final BookManager BOOK_MANAGER = new BookManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect("/");
        }
        List<Book> books = null;
        if (user.getUserType() == UserType.ADMIN) {
            books = BOOK_MANAGER.getAll();

        } else {
            books = BOOK_MANAGER.getUserBooks(user.getId());
        }
        req.setAttribute("Books", books);
        req.getRequestDispatcher("WEB-INF/books.jsp").forward(req, resp);
    }
}
