package org.example.lms_project.test.Model;

import org.example.lms_project.Model.Reservation;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReservationTest {

    @Test
    public void testDefaultConstructor() {
        Reservation reservation = new Reservation();
        assertEquals(null, reservation.getId());
        assertEquals(null, reservation.getUserId());
        assertEquals(null, reservation.getBookId());
        assertEquals(null, reservation.getReservationDate());
        assertEquals(null, reservation.getPickUpDate());
        assertEquals(null, reservation.getReturnDate());
        assertEquals(null, reservation.getStatus());
    }

    @Test
    public void testConstructorWithAllFields() {
        Date reservationDate = new Date();
        Date pickUpDate = new Date();
        Date returnDate = new Date();
        Reservation reservation = new Reservation(111L, 112L, 223L, reservationDate, pickUpDate, returnDate, "Reserved");

        assertEquals(111L, reservation.getId());
        assertEquals(112L, reservation.getUserId());
        assertEquals(223L, reservation.getBookId());
        assertEquals(reservationDate, reservation.getReservationDate());
        assertEquals(pickUpDate, reservation.getPickUpDate());
        assertEquals(returnDate, reservation.getReturnDate());
        assertEquals("Reserved", reservation.getStatus());
    }

    @Test
    public void testConstructorWithoutId() {
        Date pickUpDate = new Date();
        Date returnDate = new Date();
        Reservation reservation = new Reservation(112L, 113L, pickUpDate, returnDate, "Reserved");

        assertEquals(112L, reservation.getUserId());
        assertEquals(113L, reservation.getBookId());
        assertEquals(pickUpDate, reservation.getPickUpDate());
        assertEquals(returnDate, reservation.getReturnDate());
        assertEquals("Reserved", reservation.getStatus());
        assertEquals(new Date().getClass(), reservation.getReservationDate().getClass());
    }

    @Test
    public void testSettersAndGetters() {
        Reservation reservation = new Reservation();

        reservation.setId(145L);
        reservation.setUserId(124L);
        reservation.setBookId(311L);
        Date reservationDate = new Date();
        reservation.setReservationDate(reservationDate);
        Date pickUpDate = new Date();
        reservation.setPickUpDate(pickUpDate);
        Date returnDate = new Date();
        reservation.setReturnDate(returnDate);
        reservation.setStatus("Reserved");

        assertEquals(145L, reservation.getId());
        assertEquals(124L, reservation.getUserId());
        assertEquals(311L, reservation.getBookId());
        assertEquals(reservationDate, reservation.getReservationDate());
        assertEquals(pickUpDate, reservation.getPickUpDate());
        assertEquals(returnDate, reservation.getReturnDate());
        assertEquals("Reserved", reservation.getStatus());
    }
}

