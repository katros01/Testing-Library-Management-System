package org.example.lms_project.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.example.lms_project.Model.Book;

public class BookDAO {
    protected Connection connectToDatabase(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
        }  catch (SQLException e) {
            e.printStackTrace();
        }
        return  connection;
    }

    public void insertBook(Book book) {
        try (Connection connection = connectToDatabase(); PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO BOOK (name, quantity, description, author, genre) VALUES (?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, book.getName());
            preparedStatement.setInt(2, book.getQuantity());
            preparedStatement.setString(3, book.getDescription());
            preparedStatement.setString(4, book.getAuthor());
            preparedStatement.setString(5, book.getGenre());
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Book getBookById(Long id) {
        Book book =null;

        try (Connection connection = connectToDatabase(); PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM book WHERE id = ?;")) {
            preparedStatement.setLong(1, id);
            ResultSet result = preparedStatement.executeQuery();

            while(result.next()){
                String name = result.getString("name");
                String description = result.getString("description");
                String author = result.getString("author");
                String genre = result.getString("genre");
                int quantity = result.getInt("quantity");

                book = new Book(id, name,quantity,description,author,genre);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return book;
    }

    public List<Book> getAllBooks() {
        List<Book> books =new ArrayList<>();

        try (Connection connection = connectToDatabase(); PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM book")) {

            ResultSet result = preparedStatement.executeQuery();

            while(result.next()){
                Long id = result.getLong("id");
                String name = result.getString("name");
                String description = result.getString("description");
                String author = result.getString("author");
                String genre = result.getString("genre");
                int quantity = result.getInt("quantity");

                books.add(new Book(id, name,quantity,description,author,genre));
            }
            System.out.println("helloo");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return books;
    }

    public boolean deleteBook(Long id) {
        try(Connection connection = connectToDatabase(); PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM book WHERE id = ? ");) {
            preparedStatement.setLong(1,id);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateBook(Book book) {
        try (Connection connection = connectToDatabase(); PreparedStatement preparedStatement = connection.prepareStatement("UPDATE book SET name = ?, quantity = ?, description = ?, author = ?, genre = ? WHERE id = ?")) {
            preparedStatement.setString(1, book.getName());
            preparedStatement.setInt(2, book.getQuantity());
            preparedStatement.setString(3, book.getDescription());
            preparedStatement.setString(4, book.getAuthor());
            preparedStatement.setString(5, book.getGenre());
            preparedStatement.setLong(6, book.getId());
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

