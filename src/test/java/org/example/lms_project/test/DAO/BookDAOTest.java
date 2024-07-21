package org.example.lms_project.test.DAO;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.example.lms_project.DAO.BookDAO;
import org.example.lms_project.Model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BookDAOTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private BookDAO bookDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        bookDAO = new BookDAO() {
            @Override
            protected Connection connectToDatabase() {
                return connection;
            }
        };
    }

    @Test
    public void testInsertBook() throws SQLException {
        Book book = new Book("Test Book", 10, "Description", "Author", "Genre");

        bookDAO.insertBook(book);

        verify(preparedStatement).setString(1, "Test Book");
        verify(preparedStatement).setInt(2, 10);
        verify(preparedStatement).setString(3, "Description");
        verify(preparedStatement).setString(4, "Author");
        verify(preparedStatement).setString(5, "Genre");
        verify(preparedStatement).executeUpdate();
    }

    @Test
    public void testGetBookById() throws SQLException {
        Long bookId = 1L;
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getLong("id")).thenReturn(bookId);
        when(resultSet.getString("name")).thenReturn("Test Book");
        when(resultSet.getInt("quantity")).thenReturn(10);
        when(resultSet.getString("description")).thenReturn("Description");
        when(resultSet.getString("author")).thenReturn("Author");
        when(resultSet.getString("genre")).thenReturn("Genre");

        Book book = bookDAO.getBookById(bookId);

        assertNotNull(book);
        assertEquals(bookId, book.getId());
        assertEquals("Test Book", book.getName());
        assertEquals(10, book.getQuantity());
        assertEquals("Description", book.getDescription());
        assertEquals("Author", book.getAuthor());
        assertEquals("Genre", book.getGenre());
    }

    @Test
    public void testGetAllBooks() throws SQLException {
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, true, false);

        when(resultSet.getLong("id")).thenReturn(1L).thenReturn(2L);
        when(resultSet.getString("name")).thenReturn("Test Book 1").thenReturn("Test Book 2");
        when(resultSet.getInt("quantity")).thenReturn(10).thenReturn(20);
        when(resultSet.getString("description")).thenReturn("Description 1").thenReturn("Description 2");
        when(resultSet.getString("author")).thenReturn("Author 1").thenReturn("Author 2");
        when(resultSet.getString("genre")).thenReturn("Genre 1").thenReturn("Genre 2");

        List<Book> books = bookDAO.getAllBooks();

        assertNotNull(books);
        assertEquals(2, books.size());
        assertEquals("Test Book 1", books.get(0).getName());
        assertEquals("Test Book 2", books.get(1).getName());
    }

    @Test
    public void testDeleteBook() throws SQLException {
        Long bookId = 1L;
        when(preparedStatement.executeUpdate()).thenReturn(1);

        boolean result = bookDAO.deleteBook(bookId);

        assertTrue(result);
        verify(preparedStatement).setLong(1, bookId);
        verify(preparedStatement).executeUpdate();
    }

    @Test
    public void testUpdateBook() throws SQLException {
        Book book = new Book(1L, "Updated Book", 20, "Updated Description", "Updated Author", "Updated Genre");
        when(preparedStatement.executeUpdate()).thenReturn(1);

        boolean result = bookDAO.updateBook(book);

        assertTrue(result);
        verify(preparedStatement).setString(1, "Updated Book");
        verify(preparedStatement).setInt(2, 20);
        verify(preparedStatement).setString(3, "Updated Description");
        verify(preparedStatement).setString(4, "Updated Author");
        verify(preparedStatement).setString(5, "Updated Genre");
        verify(preparedStatement).setLong(6, 1L);
        verify(preparedStatement).executeUpdate();
    }
}

