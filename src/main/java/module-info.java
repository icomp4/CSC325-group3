module org.shadyAuto {
    requires javafx.controls;
    requires javafx.fxml;
    requires firebase.admin;
    requires com.google.auth.oauth2;
    requires com.google.auth;
    requires google.cloud.firestore;
    requires google.cloud.core;
    requires com.google.gson;
    requires org.json;
    requires java.net.http;
    requires com.google.api.apicommon;
    requires io.github.cdimascio.dotenv.java;
    requires java.logging;
    requires junit;


    exports shadyAuto;
    exports shadyAuto.FirebaseControllers;
    opens shadyAuto.FirebaseControllers to javafx.fxml;
    exports shadyAuto.Models;
    opens shadyAuto.Models to javafx.fxml;
    opens shadyAuto.ScheduleBuilder to javafx.fxml, com.google.gson;
    opens shadyAuto to com.google.gson, javafx.fxml;
    exports test to junit;

    exports shadyAuto.ScheduleBuilder;
}