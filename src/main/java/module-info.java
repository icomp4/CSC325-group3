module org.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires firebase.admin;
    requires com.google.auth.oauth2;
    requires com.google.auth;
    requires google.cloud.firestore;

    requires com.google.gson;


    exports shadyAuto;
    exports shadyAuto.FirebaseControllers;
    opens shadyAuto.FirebaseControllers to javafx.fxml;
    exports shadyAuto.Models;
    opens shadyAuto.Models to javafx.fxml;
    opens shadyAuto.ScheduleBuilder to javafx.fxml, com.google.gson;
    opens shadyAuto to com.google.gson, javafx.fxml;


    exports shadyAuto.ScheduleBuilder;
}