package com.example.mylibrary.servlet;


import com.example.mylibrary.manager.AuthorManager;
import com.example.mylibrary.model.Author;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/createAuthor")
public class CreateAuthorServlet extends HttpServlet {
    private static final AuthorManager AUTHOR_MANAGER = new AuthorManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/createAuthor.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        Author author = Author.builder().name(name)
                .surname(surname)
                .email(email)
                .age(Integer.parseInt(req.getParameter("age")))
                .build();
        AUTHOR_MANAGER.save(author);
        resp.sendRedirect("/author");
    }

}
