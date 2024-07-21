package org.example.lms_project.test.Model;

import org.example.lms_project.Model.Borrow;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BorrowTest {

    @Test
    public void testDefaultConstructor() {
        Borrow borrow = new Borrow();
        borrow.setId(1131L);
        borrow.setMemberId(211L);
        borrow.setBookId(322L);
        Date borrowDate = new Date();
        borrow.setBorrowDate(borrowDate);
        Date returnDate = new Date();
        borrow.setReturnDate(returnDate);
        borrow.setStatus("Borrowed");

        assertEquals(1131L, borrow.getId());
        assertEquals(211L, borrow.getUserId());
        assertEquals(322L, borrow.getBookId());
        assertEquals(borrowDate, borrow.getBorrowDate());
        assertEquals(returnDate, borrow.getReturnDate());
        assertEquals("Borrowed", borrow.getStatus());
    }

    @Test
    public void testParameterizedConstructorWithId() {
        Date borrowDate = new Date();
        Date returnDate = new Date();
        Borrow borrow = new Borrow(111L, 222L, 1212L, borrowDate, returnDate, "Borrowed");

        assertEquals(111L, borrow.getId());
        assertEquals(222L, borrow.getUserId());
        assertEquals(1212L, borrow.getBookId());
        assertEquals(borrowDate, borrow.getBorrowDate());
        assertEquals(returnDate, borrow.getReturnDate());
        assertEquals("Borrowed", borrow.getStatus());
    }

    @Test
    public void testParameterizedConstructorWithoutId() {
        Date returnDate = new Date();
        Borrow borrow = new Borrow(111L, 222L, returnDate, "Borrowed");

        assertEquals(111L, borrow.getUserId());
        assertEquals(222L, borrow.getBookId());
        assertEquals(returnDate, borrow.getReturnDate());
        assertEquals("Borrowed", borrow.getStatus());
    }
}

