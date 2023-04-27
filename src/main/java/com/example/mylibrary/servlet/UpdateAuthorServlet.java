package com.example.mylibrary.servlet;



import com.example.mylibrary.manager.AuthorManager;
import com.example.mylibrary.model.Author;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateAuthor")
public class UpdateAuthorServlet extends HttpServlet {
    private static  final AuthorManager AUTHOR_MANAGER = new AuthorManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Author author = AUTHOR_MANAGER.getById(id);
        req.setAttribute("author",author);
        req.getRequestDispatcher("WEB-INF/authorUpdate.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        int age = Integer.parseInt(req.getParameter("age"));
        Author author = new Author(id, name,surname,email,age);
        AUTHOR_MANAGER.update(author);
        resp.sendRedirect("/author");
    }
}
