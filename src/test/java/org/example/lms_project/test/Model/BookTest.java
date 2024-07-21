package org.example.lms_project.test.Model;


import org.example.lms_project.Model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    private Book book;

    @BeforeEach
    public void setUp() {

        book = new Book(111L, "Test Book", 10, "Description", "Author", "Genre");
    }

    @Test
    public void testBookConstructorWithId() {
        Book book = new Book(111L, "Test Book", 10, "Description", "Author", "Genre");
        assertNotNull(book);
        assertEquals(111L, book.getId());
        assertEquals("Test Book", book.getName());
        assertEquals(10, book.getQuantity());
        assertEquals("Description", book.getDescription());
        assertEquals("Author", book.getAuthor());
        assertEquals("Genre", book.getGenre());
    }

    @Test
    public void testBookConstructorWithoutId() {
        Book book = new Book("Test Book", 10, "Description", "Author", "Genre");
        assertNotNull(book);
        assertNull(book.getId());
        assertEquals("Test Book", book.getName());
        assertEquals(10, book.getQuantity());
        assertEquals("Description", book.getDescription());
        assertEquals("Author", book.getAuthor());
        assertEquals("Genre", book.getGenre());
    }

    @Test
    public void testGetters() {
        assertEquals(111L, book.getId());
        assertEquals("Test Book", book.getName());
        assertEquals(10, book.getQuantity());
        assertEquals("Description", book.getDescription());
        assertEquals("Author", book.getAuthor());
        assertEquals("Genre", book.getGenre());
    }

    @Test
    public void testSetters() {
        book.setName("New Name");
        book.setQuantity(20);
        book.setDescription("New Description");
        book.setAuthor("New Author");
        book.setGenre("New Genre");

        assertEquals("New Name", book.getName());
        assertEquals(20, book.getQuantity());
        assertEquals("New Description", book.getDescription());
        assertEquals("New Author", book.getAuthor());
        assertEquals("New Genre", book.getGenre());
    }
}

