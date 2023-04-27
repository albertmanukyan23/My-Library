package com.example.mylibrary.servlet;



import com.example.mylibrary.manager.AuthorManager;
import com.example.mylibrary.model.Author;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/author")
public class AuthorServlet extends HttpServlet {
    private static  final AuthorManager AUTHOR_MANAGER = new AuthorManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Author> all = AUTHOR_MANAGER.getAll();
        req.setAttribute("allAuthors", all);
        req.getRequestDispatcher("WEB-INF/author.jsp").forward(req,resp);

    }
}
