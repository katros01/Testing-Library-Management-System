package org.example.lms_project.controllers;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import org.example.lms_project.DAO.BookDAO;
import org.example.lms_project.DAO.BorrowDAO;
import org.example.lms_project.DAO.UserDAO;
import org.example.lms_project.Model.Book;
import org.example.lms_project.Model.Borrow;
import org.example.lms_project.Model.User;

public class BorrowersPageController implements Initializable {

    @FXML
    private TableView<Borrow> borrowsTable;
    @FXML
    private TableColumn<Borrow, Long> idCol;
    @FXML
    private TableColumn<Borrow, String> nameCol;
    @FXML
    private TableColumn<Borrow, String> bookCol;
    @FXML
    private TableColumn<Borrow, String> dateCol;
    @FXML
    private TableColumn<Borrow, String> returnDateCol;
    @FXML
    private TableColumn<Borrow, String> statusCol;

    private final BorrowDAO borrowDAO = new BorrowDAO();
    private final UserDAO userDAO = new UserDAO();
    private final BookDAO bookDAO = new BookDAO();
    @FXML
    private TableColumn<Borrow, Void> actionCol;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         // Initialize table columns
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
        dateCol.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        returnDateCol.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        actionCol.setCellFactory(new Callback<TableColumn<Borrow, Void>, TableCell<Borrow, Void>>() {
            @Override
            public TableCell<Borrow, Void> call(TableColumn<Borrow, Void> param) {
                return new TableCell<Borrow, Void>() {
                    private final Button btn = new Button("Update Status");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Borrow borrow = getTableView().getItems().get(getIndex());
                            openStatusUpdateDialog(borrow);
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
            }
        });

        getBorrows();
    }
    
    private void getBorrows() {
        try {
            List<Borrow> borrows = borrowDAO.getAllBorrows();
            borrowsTable.getItems().addAll(borrows);
        } catch (SQLException ex) {
            Logger.getLogger(BorrowersPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void openStatusUpdateDialog(Borrow borrow) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/lms_project/UpdateStatusPage.fxml"));
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UTILITY);
            stage.setScene(new Scene(loader.load()));
            UpdateStatusPageController controller = loader.getController();
            controller.initData(borrow);
            stage.showAndWait();
            borrowsTable.getItems().clear();
            getBorrows();
        } catch (IOException ex) {
            Logger.getLogger(BorrowersPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
