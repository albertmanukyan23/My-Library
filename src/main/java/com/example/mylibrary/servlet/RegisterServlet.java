package com.example.mylibrary.servlet;

import com.example.mylibrary.manager.UserManager;
import com.example.mylibrary.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final UserManager USER_MANAGER = new UserManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        User user = USER_MANAGER.getByEmail(email);
        if (user == null) {
            USER_MANAGER.save(User.builder()
                    .name(req.getParameter("name"))
                    .surname(req.getParameter("surname"))
                    .email(email)
                    .password(req.getParameter("password"))
                    .build());
        }
        resp.sendRedirect("/");
    }
}