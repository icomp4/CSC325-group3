package shadyAuto;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.FirebaseAuth;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ShadyAuto extends Application {
    public static Firestore fstore;
    public static FirebaseAuth fauth;
    private final FirestoreContext contxtFirebase = new FirestoreContext();
    @Override
    public void start(Stage stage) throws IOException {

        fstore = contxtFirebase.firebase();
        fauth = FirebaseAuth.getInstance();
        //update this line as much as you can
        FXMLLoader fxmlLoader = new FXMLLoader(ShadyAuto.class.getResource("schedule-builder.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("shadyAuto");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }

}