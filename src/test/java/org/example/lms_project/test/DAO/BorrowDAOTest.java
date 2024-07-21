package org.example.lms_project.test.DAO;


import org.example.lms_project.DAO.BorrowDAO;
import org.example.lms_project.Model.Borrow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class BorrowDAOTest {

    @InjectMocks
    private BorrowDAO borrowDAO;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        borrowDAO = new BorrowDAO(){
            @Override
            protected Connection getConnection() {
                return connection;
            }
        };
    }

    @Test
    void testAddBorrow() throws SQLException {

        Borrow borrow = new Borrow(1L, 2L, 3L, new Date(), new Date(), "status");

        borrowDAO.addBorrow(borrow);

        verify(preparedStatement).setLong(1, borrow.getUserId());
        verify(preparedStatement).setLong(2, borrow.getBookId());
        verify(preparedStatement).setDate(3, new java.sql.Date(borrow.getBorrowDate().getTime()));
        verify(preparedStatement).setDate(4, new java.sql.Date(borrow.getReturnDate().getTime()));
        verify(preparedStatement).setString(5, borrow.getStatus());
        verify(preparedStatement).executeUpdate();
    }

    @Test
    void testGetBorrow() throws SQLException {
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getLong("id")).thenReturn(1L);
        when(resultSet.getLong("userId")).thenReturn(2L);
        when(resultSet.getLong("bookId")).thenReturn(3L);
        when(resultSet.getDate("borrowDate")).thenReturn(new java.sql.Date(new Date().getTime()));
        when(resultSet.getDate("returnDate")).thenReturn(new java.sql.Date(new Date().getTime()));
        when(resultSet.getString("status")).thenReturn("status");

        Borrow borrow = borrowDAO.getBorrow(1);

        assertNotNull(borrow);
        assertEquals(1L, borrow.getId());
        assertEquals(2L, borrow.getUserId());
        assertEquals(3L, borrow.getBookId());
        assertEquals("status", borrow.getStatus());
    }

    @Test
    void testUpdateBorrow() throws SQLException {

        Borrow borrow = new Borrow(1L, 2L, 3L, new Date(), new Date(), "status");

        borrowDAO.updateBorrow(borrow);

        verify(preparedStatement).setLong(1, borrow.getUserId());
        verify(preparedStatement).setLong(2, borrow.getBookId());
        verify(preparedStatement).setDate(3, new java.sql.Date(borrow.getBorrowDate().getTime()));
        verify(preparedStatement).setDate(4, new java.sql.Date(borrow.getReturnDate().getTime()));
        verify(preparedStatement).setString(5, borrow.getStatus());
        verify(preparedStatement).setLong(6, borrow.getId());
        verify(preparedStatement).executeUpdate();
    }

    @Test
    void testDeleteBorrow() throws SQLException {

        borrowDAO.deleteBorrow(1);
        verify(preparedStatement).setInt(1, 1);
        verify(preparedStatement).executeUpdate();
    }

    @Test
    void testGetAllBorrows() throws SQLException {

        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getLong("id")).thenReturn(1L);
        when(resultSet.getLong("userId")).thenReturn(2L);
        when(resultSet.getLong("bookId")).thenReturn(3L);
        when(resultSet.getDate("borrowDate")).thenReturn(new java.sql.Date(new Date().getTime()));
        when(resultSet.getDate("returnDate")).thenReturn(new java.sql.Date(new Date().getTime()));
        when(resultSet.getString("status")).thenReturn("status");

        List<Borrow> borrows = borrowDAO.getAllBorrows();

        assertNotNull(borrows);
        assertEquals(1, borrows.size());
        Borrow borrow = borrows.get(0);
        assertEquals(1L, borrow.getId());
        assertEquals(2L, borrow.getUserId());
        assertEquals(3L, borrow.getBookId());
        assertEquals("status", borrow.getStatus());
    }

    @Test
    void testGetBorrowsByUserId() throws SQLException {

        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getLong("id")).thenReturn(1L);
        when(resultSet.getLong("userId")).thenReturn(2L);
        when(resultSet.getLong("bookId")).thenReturn(3L);
        when(resultSet.getDate("borrowDate")).thenReturn(new java.sql.Date(new Date().getTime()));
        when(resultSet.getDate("returnDate")).thenReturn(new java.sql.Date(new Date().getTime()));
        when(resultSet.getString("status")).thenReturn("status");

        List<Borrow> borrows = borrowDAO.getBorrowsByUserId(2L);

        assertNotNull(borrows);
        assertEquals(1, borrows.size());
        Borrow borrow = borrows.get(0);
        assertEquals(1L, borrow.getId());
        assertEquals(2L, borrow.getUserId());
        assertEquals(3L, borrow.getBookId());
        assertEquals("status", borrow.getStatus());
    }
}

