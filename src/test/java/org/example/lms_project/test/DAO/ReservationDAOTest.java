package org.example.lms_project.test.DAO;

import org.example.lms_project.DAO.BorrowDAO;
import org.example.lms_project.DAO.ReservationDAO;
import org.example.lms_project.Model.Borrow;
import org.example.lms_project.Model.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ReservationDAOTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private ResultSet mockResultSet;

    @InjectMocks
    private ReservationDAO reservationDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        reservationDAO = new ReservationDAO(){
            @Override
            protected Connection connectToDatabase() {
                return mockConnection;
            }
        };
    }

    @Test
    public void testInsertReservation() throws Exception {
        Reservation reservation = new Reservation(1L, 1L, new Date(), new Date(), "Reserved");

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        reservationDAO.insertReservation(reservation);

        verify(mockPreparedStatement, times(1)).setLong(1, reservation.getUserId());
        verify(mockPreparedStatement, times(1)).setLong(2, reservation.getBookId());
        verify(mockPreparedStatement, times(1)).setDate(3, new java.sql.Date(reservation.getPickUpDate().getTime()));
        verify(mockPreparedStatement, times(1)).setDate(4, new java.sql.Date(reservation.getReturnDate().getTime()));
        verify(mockPreparedStatement, times(1)).setString(5, reservation.getStatus());
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testGetAllReservations() throws Exception {
        List<Reservation> expectedReservations = new ArrayList<>();
        Reservation reservation = new Reservation(1L, 1L, 1L, new Date(), new Date(), new Date(), "Reserved");
        expectedReservations.add(reservation);

        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getLong("id")).thenReturn(reservation.getId());
        when(mockResultSet.getLong("userId")).thenReturn(reservation.getUserId());
        when(mockResultSet.getLong("bookId")).thenReturn(reservation.getBookId());
        when(mockResultSet.getDate("reservationDate")).thenReturn(new java.sql.Date(reservation.getReservationDate().getTime()));
        when(mockResultSet.getDate("pickUpDate")).thenReturn(new java.sql.Date(reservation.getPickUpDate().getTime()));
        when(mockResultSet.getDate("returnDate")).thenReturn(new java.sql.Date(reservation.getReturnDate().getTime()));
        when(mockResultSet.getString("status")).thenReturn(reservation.getStatus());

        List<Reservation> actualReservations = reservationDAO.getAllReservations();

        // Assert
        assertNotNull(actualReservations);
        assertEquals(1, actualReservations.size());
        Reservation actualReservation = actualReservations.get(0);
        assertEquals(1L, actualReservation.getId());
        assertEquals(1L, actualReservation.getUserId());
        assertEquals(1L, actualReservation.getBookId());
        assertEquals("Reserved", actualReservation.getStatus());

    }

    @Test
    public void testGetReservationsByUserId() throws Exception {
        Long userId = 1L;
        Reservation reservation = new Reservation(1L, userId, 1L, new Date(), new Date(), new Date(), "Reserved");

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getLong("id")).thenReturn(reservation.getId());
        when(mockResultSet.getLong("userId")).thenReturn(reservation.getUserId());
        when(mockResultSet.getLong("bookId")).thenReturn(reservation.getBookId());
        when(mockResultSet.getDate("reservationDate")).thenReturn(new java.sql.Date(reservation.getReservationDate().getTime()));
        when(mockResultSet.getDate("pickUpDate")).thenReturn(new java.sql.Date(reservation.getPickUpDate().getTime()));
        when(mockResultSet.getDate("returnDate")).thenReturn(new java.sql.Date(reservation.getReturnDate().getTime()));
        when(mockResultSet.getString("status")).thenReturn(reservation.getStatus());

        List<Reservation> retrievedReservation = reservationDAO.getReservationsByUserId(reservation.getUserId());

        // Assert
        assertNotNull(retrievedReservation);
        assertEquals(1, retrievedReservation.size());
        Reservation actualReservation = retrievedReservation.get(0);
        assertEquals(1L, actualReservation.getId());
        assertEquals(1L, actualReservation.getUserId());
        assertEquals(1L, actualReservation.getBookId());
        assertEquals("Reserved", actualReservation.getStatus());
    }
}

