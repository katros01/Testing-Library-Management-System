package org.example.lms_project.controllers;


import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import org.example.lms_project.DAO.BorrowDAO;
import org.example.lms_project.Model.Borrow;


public class UpdateStatusPageController implements Initializable {

    @FXML
    private ComboBox<String> statusId;

    
    private Borrow borrowToUpdate;

    private final BorrowDAO borrowDAO = new BorrowDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void initData(Borrow borrow) {
        this.borrowToUpdate = borrow;
        
        statusId.getItems().addAll("Borrowed", "Returned", "Overdue");
    }

    @FXML
    private void updateStatusButton(ActionEvent event) {
        String newStatus = statusId.getValue();
        if (newStatus != null) {
            try {
                borrowToUpdate.setStatus(newStatus);
                
                borrowDAO.updateBorrow(borrowToUpdate);
                closeStage();
            } catch (SQLException ex) {
                Logger.getLogger(UpdateStatusPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else {
            
        }
    }

    @FXML
    private void cancelButton(ActionEvent event) {
        closeStage();
    }
    
    private void closeStage() {
        Stage stage = (Stage) statusId.getScene().getWindow();
        stage.close();
    }
    
}
