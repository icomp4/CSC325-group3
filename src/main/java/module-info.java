module org.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires firebase.admin;
    requires com.google.auth.oauth2;
    requires com.google.auth;


    opens org.example.demo1 to javafx.fxml;
    exports org.example.demo1;
}