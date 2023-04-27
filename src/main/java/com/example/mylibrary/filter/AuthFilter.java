package com.example.mylibrary.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter(urlPatterns = {"/home","/books","/author","/createAuthor","/createBook"})
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (javax.servlet.http.HttpServletRequest) servletRequest;
        HttpServletResponse resp = (javax.servlet.http.HttpServletResponse) servletResponse;
        if (req.getSession().getAttribute("user") == null){
            resp.sendRedirect("/");
        }
        else
            filterChain.doFilter(req,resp);
    }
}
