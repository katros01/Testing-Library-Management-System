package org.example.lms_project.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.lms_project.DAO.BookDAO;
import org.example.lms_project.Model.Book;


public class BooksPageController implements Initializable {

    @FXML
    private AnchorPane frame;
    @FXML
    private TableView<Book> listBooks;
    @FXML
    private TableColumn<Book, Long> idCol;
    @FXML
    private TableColumn<Book, String> nameCol;
    @FXML
    private TableColumn<Book, Integer> quantityCol;
    @FXML
    private TableColumn<Book, String> authorCol;
    @FXML
    private TableColumn<Book, String> genreCol;
    @FXML
    private TableColumn<Book, Void> actionCol;

    BookDAO bookDAO = new BookDAO();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();

        List<Book> books = bookDAO.getAllBooks();
        ObservableList<Book> allBooks = FXCollections.observableArrayList(bookDAO.getAllBooks());
        listBooks.setItems(allBooks);
        System.out.println("@@@@@@@@@@@");
    }

    @FXML
    private void getAddBook(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/org/example/lms_project/AddBook.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(BooksPageController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void initTable() {

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        genreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));

        actionCol.setCellFactory(param -> new TableCell<>() {
            private final Button editButton = new Button("Edit");
            private final Button deleteButton = new Button("Delete");

            {
                editButton.setOnAction(event -> {
                    Book book = getTableView().getItems().get(getIndex());
                    editBook(book);
                });

                deleteButton.setOnAction(event -> {
                    Book book = getTableView().getItems().get(getIndex());
                    deleteBook(book);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(new javafx.scene.layout.HBox(editButton, deleteButton));
                }
            }
        });
    }

    private void editBook(Book book) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/lms_project/EditBookPage.fxml"));
            Parent parent = loader.load();

            EditBookPageController controller = loader.getController();
            controller.initData(book);

            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.showAndWait();

            List<Book> books = bookDAO.getAllBooks();
            ObservableList<Book> allBooks = FXCollections.observableArrayList(books);
            listBooks.setItems(allBooks);

        } catch (IOException ex) {
            Logger.getLogger(BooksPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void deleteBook(Book book) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setHeaderText("Deleting Book: " + book.getName());
        alert.setContentText("Are you sure you want to delete this book?");

        if (alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            try {
                if (bookDAO.deleteBook(book.getId())) {
                    Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
                    confirmation.setTitle("Delete Successful");
                    confirmation.setHeaderText(null);
                    confirmation.setContentText("Book deleted successfully.");
                    confirmation.showAndWait();

                    List<Book> books = bookDAO.getAllBooks();
                    ObservableList<Book> allBooks = FXCollections.observableArrayList(books);
                    listBooks.setItems(allBooks);

                } else {
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Delete Failed");
                    error.setHeaderText(null);
                    error.setContentText("Failed to delete book. Please try again.");
                    error.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
                // Handle exception
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Delete Error");
                error.setHeaderText(null);
                error.setContentText("An error occurred while deleting the book.");
                error.showAndWait();
            }
        }

    }

}

