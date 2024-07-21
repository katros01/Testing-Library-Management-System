package org.example.lms_project.test.integration;



import org.example.lms_project.DAO.BookDAO;
import org.example.lms_project.DAO.ReservationDAO;
import org.example.lms_project.DAO.UserDAO;
import org.example.lms_project.Model.Book;
import org.example.lms_project.Model.User;
import org.example.lms_project.controllers.ReservePageController;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ReserveBookTest {

    @Mock
    private UserDAO userDAO;
    @Mock
    private BookDAO bookDAO;
    @Mock
    private ReservationDAO reservationDAO;

    @InjectMocks
    private ReservePageController controller;


    @BeforeAll
    public static void setupBeforeAll() {

    }

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetBookIdByName() {
        List<Book> books = Arrays.asList(
                new Book(1L, "Book1", 10, "Description1", "Author1", "Genre1"),
                new Book(2L, "Book2", 5, "Description2", "Author2", "Genre2")
        );

        controller.bookList = books;

        Long bookId = controller.getBookIdByName("Book1");

        assertEquals(1L, bookId);
    }

    @Test
    public void testGetUserIdByEmail() {
        List<User> users = Arrays.asList(
                new User(1L, "Alice", "alice@example.com", "User"),
                new User(2L, "Bob", "bob@example.com", "User")
        );

        controller.userList = users;

        Long userId = controller.getUserIdByEmail("alice@example.com");

        assertEquals(1L, userId);
    }
}
