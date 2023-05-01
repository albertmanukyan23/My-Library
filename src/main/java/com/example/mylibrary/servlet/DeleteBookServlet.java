package com.example.mylibrary.servlet;


import com.example.mylibrary.constants.GetSharedConstants;
import com.example.mylibrary.manager.BookManager;
import com.example.mylibrary.model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet("/deleteBook")
public class DeleteBookServlet extends HttpServlet {
    private static final BookManager BOOK_MANAGER = new BookManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Book book = BOOK_MANAGER.getById(id);
        if (book != null){
            if (book.getPicName() != null){
                File file = new File(GetSharedConstants.UPLOAD_FOLDER + book.getPicName());
                if (file.exists()) {
                    file.delete();
                }
            }
        }
        BOOK_MANAGER.removeById(id);
        resp.sendRedirect("/books");
    }
}
