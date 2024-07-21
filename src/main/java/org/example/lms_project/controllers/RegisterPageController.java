package org.example.lms_project.controllers;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.lms_project.DAO.UserDAO;
import org.example.lms_project.Model.User;

public class RegisterPageController implements Initializable {

    @FXML
    private TextField nameId;
    @FXML
    private TextField emailId;

   private UserDAO userDAO;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userDAO = new UserDAO();
    }    

    @FXML
    private void registerButton(ActionEvent event) {
        String name = nameId.getText();
        String email = emailId.getText();

        if (name.isEmpty() || email.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter name and email.", null);
            return;
        }

        User newUser = new User(name, email, "regular");

        try {
 
            userDAO.insertUser(newUser);

            showAlert(Alert.AlertType.INFORMATION, "Success", "User registered successfully.", null);

            nameId.clear();
            emailId.clear();
            closeStage();

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to register user.", e.getMessage());
        }
    }

    @FXML
    private void cancelButton(ActionEvent event) {
        closeStage();
    }
    
     private void showAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
     
     private void closeStage() {
        Stage stage = (Stage) nameId.getScene().getWindow();
        stage.close();
    }
    
}
