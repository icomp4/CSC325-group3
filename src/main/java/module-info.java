module org.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires firebase.admin;
    requires com.google.auth.oauth2;
    requires com.google.auth;
    requires google.cloud.firestore;


    opens shadyAuto to javafx.fxml;
    exports shadyAuto;
    exports shadyAuto.FirebaseControllers;
    opens shadyAuto.FirebaseControllers to javafx.fxml;
    exports shadyAuto.Models;
    opens shadyAuto.Models to javafx.fxml;
}