package org.example.lms_project.controllers;



import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.lms_project.DAO.BookDAO;
import org.example.lms_project.DAO.ReservationDAO;
import org.example.lms_project.DAO.UserDAO;
import org.example.lms_project.Model.Book;
import org.example.lms_project.Model.Reservation;
import org.example.lms_project.Model.User;

public class ReservationsPageController implements Initializable {

    @FXML
    private TableView<Reservation> reservationsTable;
    @FXML
    private TableColumn<Reservation, Long> idCol;
    @FXML
    private TableColumn<Reservation, String> nameCol;
    @FXML
    private TableColumn<Reservation, String> bookCol;
    @FXML
    private TableColumn<Reservation, String> dateCol;
    @FXML
    private TableColumn<Reservation, String> reservationCol;
    @FXML
    private TableColumn<Reservation, String> returnDateCol;
    @FXML
    private TableColumn<Reservation, String> statusCol;

    private final ReservationDAO reservationDAO = new ReservationDAO();
    private final UserDAO userDAO = new UserDAO();
    private final BookDAO bookDAO = new BookDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
         nameCol.setCellValueFactory(cellData -> {
            Long userId = cellData.getValue().getUserId();
            User user = userDAO.getUserById(userId);
            return new SimpleStringProperty(user.getName());
        });
        bookCol.setCellValueFactory(cellData -> {
            Long bookId = cellData.getValue().getBookId();
            Book book = bookDAO.getBookById(bookId);
            return new SimpleStringProperty(book.getName());
        });
        dateCol.setCellValueFactory(new PropertyValueFactory<>("pickUpDate"));
        returnDateCol.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        reservationCol.setCellValueFactory(new PropertyValueFactory<>("reservationDate"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadReservations();
    }
     private void loadReservations() {
        List<Reservation> reservations = reservationDAO.getAllReservations();
        reservationsTable.getItems().addAll(reservations);
    }
    
}
