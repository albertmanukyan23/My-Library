package com.example.mylibrary.servlet;

import com.example.mylibrary.manager.UserManager;
import com.example.mylibrary.model.User;
import com.example.mylibrary.model.UserType;
import com.example.mylibrary.util.EmailUtil;

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
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        UserType userType = UserType.valueOf(req.getParameter("userType"));
        String msg = "";
        if (name == null || name.trim().equals("")){
            msg+= "name is required<br>";
        }
        if (surname == null || surname.trim().equals("")) {
            msg += "surname is required<br>";
        } 
        if (email == null || email.trim().equals("")) {
            msg += "email is required<br>";
        } else if (!EmailUtil.patternMatches(email)) {
            msg += "email format is required<br>";
        }
        if (password == null || password.trim().equals("")) {
            msg += "password is required<br>";
        } else if (password.length() < 6 ) {
            msg += "password length must be >= 6<br>";
        }
        if(msg.equals("")){
            User user = USER_MANAGER.getEmail(email);
            if (user == null) {
                USER_MANAGER.save(User.builder()
                        .name(name)
                        .surname(surname)
                        .email(email)
                        .password(password)
                        .userType(userType)
                        .build());
            }
            resp.sendRedirect("/");
        }else {
            req.setAttribute("msg",msg);
            req.getRequestDispatcher("/register.jsp").forward(req,resp);
        }


    }
}
