package com.example.mylibrary.manager;




import com.example.mylibrary.db.DBConnectionProvider;
import com.example.mylibrary.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookManager {

    private static final Connection CONNECTION = DBConnectionProvider.getInstance().getConnection();
    private static final AuthorManager AUTHOR_MANAGER = new AuthorManager();

    public void save(Book book) {
        String sql = "INSERT INTO book(title,description,price,author_id) VALUES(?,?,?,?)";
        try (PreparedStatement statement = CONNECTION.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getDescription());
            statement.setInt(3, book.getPrice());
            statement.setInt(4, book.getAuthor().getId());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                book.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Book getById(int id) {
        try (Statement statement = CONNECTION.createStatement()) {
            ResultSet resultSet = statement.executeQuery("Select * from book where id = " + id);
            if (resultSet.next()) {
                return getEmployeeFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Book> getAll() {
        List<Book> books = new ArrayList<>();
        try {
            Statement statement = CONNECTION.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from book");
            while (resultSet.next()) {
                books.add(getEmployeeFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }



    private Book getEmployeeFromResultSet(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getInt("id"));
        book.setTitle(resultSet.getString("title"));
        book.setDescription(resultSet.getString("description"));
        book.setPrice(resultSet.getInt("price"));
        int authorId = resultSet.getInt("author_id");
        book.setAuthor(AUTHOR_MANAGER.getById(authorId));
        return book;
    }

    public void update(Book book) {
        String sql = "UPDATE book SET title = ?, description = ?, price = ?, author_id = ? WHERE id =" + book.getId();
        try (PreparedStatement statement = CONNECTION.prepareStatement(sql)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getDescription());
            statement.setInt(3, book.getPrice());
            statement.setInt(4, book.getAuthor().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void removeById(int bookId) {
        String sql = "DELETE FROM book WHERE id = " + bookId;
        try (Statement statement = CONNECTION.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}