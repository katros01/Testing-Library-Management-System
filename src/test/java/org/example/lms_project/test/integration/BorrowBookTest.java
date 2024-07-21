package org.example.lms_project.test.integration;

import org.example.lms_project.DAO.BorrowDAO;
import org.example.lms_project.Model.Borrow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.util.Date;

import static org.mockito.Mockito.*;


public class BorrowBookTest {
    @Mock
    private BorrowDAO borrowDAO;

    @InjectMocks
    private BorrowBookTest borrowDAOIntegrationTest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddBorrow() throws SQLException {
        Borrow borrow = new Borrow(1L, 2L, new Date(), "borrowed");

        doNothing().when(borrowDAO).addBorrow(borrow);

        borrowDAO.addBorrow(borrow);

        verify(borrowDAO, times(1)).addBorrow(borrow);
    }

    @Test
    public void testUpdateBorrow() throws SQLException {
        Borrow borrow = new Borrow(1L, 2L, 3L, new Date(), new Date(), "returned");

        doNothing().when(borrowDAO).updateBorrow(borrow);

        borrowDAO.updateBorrow(borrow);

        verify(borrowDAO, times(1)).updateBorrow(borrow);
    }

    @ParameterizedTest
    @CsvSource({
            "1, 1, 2, 3, borrowed",
            "2, 2, 3, 4, returned"
    })
    public void testParameterizedUpdateBorrow(Long id, Long userId, Long bookId, Long returnDateOffset, String status) throws SQLException {
        Date borrowDate = new Date();
        Date returnDate = new Date(borrowDate.getTime() + returnDateOffset * 24 * 60 * 60 * 1000);
        Borrow borrow = new Borrow(id, userId, bookId, borrowDate, returnDate, status);

        doNothing().when(borrowDAO).updateBorrow(borrow);

        borrowDAO.updateBorrow(borrow);

        verify(borrowDAO, times(1)).updateBorrow(borrow);
    }
}
