package org.example.lms_project;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static  Stage stage;
    @Override
    public void start(Stage primaryStage) {
        try {
            stage =primaryStage;
            Parent  root = FXMLLoader.load(getClass().getResource("/org/example/lms_project/landingFXML.fxml"));

            Scene scene = new Scene(root, 900, 500);

            primaryStage.setTitle("Katros Library");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void changeScene(String fxml) throws IOException{
        Parent  pane = FXMLLoader.load(getClass().getResource(fxml));
        stage.getScene().setRoot(pane);
    }

    public static void main(String[] args) {
        launch(args);
    }

}