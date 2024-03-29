module com.example.csc325project {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.csc325project to javafx.fxml;
    exports com.example.csc325project;
}