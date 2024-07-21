package org.example.lms_project.controllers;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class UpdateBookController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField quantity;
    @FXML
    private TextField author;
    @FXML
    private TextField genre;
    @FXML
    private Button edit;
    @FXML
    private Button cancel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void editBook(ActionEvent event) {
    }

    @FXML
    private void cancelButton(ActionEvent event) {
    }
    
}
