package org.example.demo1;

import com.google.auth.oauth2.GoogleCredentials;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.google.firebase.*;
import java.io.FileInputStream;
import java.io.IOException;

public class ShadyAuto extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream("key.jason");

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);

        FXMLLoader fxmlLoader = new FXMLLoader(ShadyAuto.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("shadyAuto");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }
}