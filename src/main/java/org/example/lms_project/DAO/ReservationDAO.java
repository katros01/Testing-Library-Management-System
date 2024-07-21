package org.example.lms_project.DAO;

import org.example.lms_project.Model.Reservation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {

    protected Connection connectToDatabase() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertReservation(Reservation reservation) {
        try (Connection connection = connectToDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO reservation (userId, bookId, pickUpDate, returnDate, status) VALUES (?, ?, ?, ?, ?)")) {

            preparedStatement.setLong(1, reservation.getUserId());
            preparedStatement.setLong(2, reservation.getBookId());
            preparedStatement.setDate(3, new java.sql.Date(reservation.getPickUpDate().getTime()));
            preparedStatement.setDate(4, new java.sql.Date(reservation.getReturnDate().getTime()));
            preparedStatement.setString(5, reservation.getStatus());

            preparedStatement.executeUpdate();
            System.out.println("Reservation inserted successfully.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();

        try (Connection connection = connectToDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM reservation")) {

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Long id = result.getLong("id");
                Long userId = result.getLong("userId");
                Long bookId = result.getLong("bookId");
                Date reservationDate = result.getDate("reservationDate");
                Date pickUpDate = result.getDate("pickUpDate");
                Date returnDate = result.getDate("returnDate");
                String status = result.getString("status");

                Reservation reservation = new Reservation(id, userId, bookId, reservationDate, pickUpDate, returnDate, status);
                reservation.setId(id);
                reservations.add(reservation);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return reservations;
    }

    public List<Reservation> getReservationsByUserId(Long userId) throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservation WHERE userId = ?";
        try (Connection conn = connectToDatabase(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Reservation reservation = new Reservation(
                            rs.getLong("id"),
                            rs.getLong("userId"),
                            rs.getLong("bookId"),
                            rs.getDate("reservationDate"),
                            rs.getDate("pickUpDate"),
                            rs.getDate("returnDate"),
                            rs.getString("status")
                    );
                    reservations.add(reservation);
                }
            }
        }
        return reservations;
    }
}


