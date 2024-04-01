module com.example.csc325project {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.google.gson;


    opens com.example.csc325project to javafx.fxml, com.google.gson;
    exports com.example.csc325project;
}