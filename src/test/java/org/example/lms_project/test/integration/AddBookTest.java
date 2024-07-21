package org.example.lms_project.test.integration;

import org.example.lms_project.DAO.BookDAO;
import org.example.lms_project.Model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static org.mockito.Mockito.*;


public class AddBookTest {

    @Mock
    private BookDAO bookDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddBook() throws SQLException {
        Book book = new Book(1L, "Test Book", 10, "Test Description", "Test Author", "Test Genre");
        doNothing().when(bookDAO).insertBook(book);
        bookDAO.insertBook(book);
        verify(bookDAO, times(1)).insertBook(book);
    }

}
