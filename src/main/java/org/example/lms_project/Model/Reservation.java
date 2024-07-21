package org.example.lms_project.Model;

import java.util.Date;

public class Reservation {
    private Long id;
    private Long userId;
    private Long bookId;
    private Date reservationDate;
    private Date pickUpDate;
    private Date returnDate;
    private String status;

    public Reservation() {}

    public Reservation(Long id, Long userId, Long bookId, Date reservationDate, Date pickUpDate, Date returnDate, String status) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.reservationDate = reservationDate;
        this.pickUpDate = pickUpDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public Reservation(Long userId, Long bookId, Date pickUpDate, Date returnDate, String status) {
        this.userId = userId;
        this.bookId = bookId;
        this.reservationDate = new Date();
        this.pickUpDate = pickUpDate;
        this.returnDate = returnDate;
        this.status = status;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Date getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(Date pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

