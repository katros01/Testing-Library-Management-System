module org.example.lms_project {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.logging;
    requires java.sql;
    requires org.mockito;

//    opens org.example.lms_project to javafx.fxml;
    opens org.example.lms_project.controllers to javafx.fxml;
    opens org.example.lms_project.DAO to javafx.base;
    opens org.example.lms_project to org.mockito;
    exports org.example.lms_project;
    exports org.example.lms_project.controllers;
    exports org.example.lms_project.Model;
    opens org.example.lms_project.Model to javafx.base, org.mockito;
}