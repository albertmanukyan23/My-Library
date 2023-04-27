package com.example.mylibrary.servlet;

import com.example.mylibrary.manager.UserManager;
import com.example.mylibrary.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")

public class LoginServlet extends HttpServlet {
    private static final UserManager USER_MANAGER = new UserManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User byEmailAndPassword = USER_MANAGER.getEmailPassword(email, password);
        HttpSession session = req.getSession();
        if (byEmailAndPassword == null) {
            session.setAttribute("message", "Login or password is incorrect");
            resp.sendRedirect("/");
        } else {
            session.setAttribute("user", byEmailAndPassword);
            resp.sendRedirect("/home");

        }

    }
}
