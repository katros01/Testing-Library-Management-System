package org.example.lms_project.controllers;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.example.lms_project.DAO.BookDAO;
import org.example.lms_project.Model.Book;

public class EditBookPageController implements Initializable {

    private Book book;
    @FXML
    private TextField nameId;
    @FXML
    private TextField quantityId;
    @FXML
    private TextField authorId;
    @FXML
    private TextField genreId;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initData(Book book) {
        this.book = book;
        nameId.setText(book.getName());
        quantityId.setText(String.valueOf(book.getQuantity()));
        authorId.setText(book.getAuthor());
        genreId.setText(book.getGenre());
    }

    @FXML
    private void editButon(ActionEvent event) {
         book.setName(nameId.getText());
        book.setQuantity(Integer.parseInt(quantityId.getText()));
        book.setAuthor(authorId.getText());
        book.setGenre(genreId.getText());

        BookDAO bookDAO = new BookDAO();
        bookDAO.updateBook(book);

        closeStage();
    }

    @FXML
    private void cancelButton(ActionEvent event) {
        closeStage();
    }
    
    private void closeStage() {
        Stage stage = (Stage) nameId.getScene().getWindow();
        stage.close();
    }
    
}
