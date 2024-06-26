package shadyAuto;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.FirebaseAuth;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import shadyAuto.FirebaseControllers.FirestoreDBConnection;
import shadyAuto.FirebaseControllers.InvoiceController;
import shadyAuto.FirebaseControllers.UserController;

import java.io.IOException;

public class ShadyAuto extends Application {
    //********************************
    //Variables
    //********************************
    public static Scene scene;
    public static Firestore fstore;
    public static FirebaseAuth fauth;
    private final FirestoreContext contxtFirebase = new FirestoreContext();

    //fields to grab Stage Windows
    private static Stage primaryStage;
    public static FirestoreDBConnection db = new FirestoreDBConnection();




    //********************************
    //Methods
    //********************************
    public static Stage getPrimaryStage(){
        return primaryStage;
    }
    @Override
    public void start(Stage stage) throws IOException {


        primaryStage = stage;

        fstore = contxtFirebase.firebase();
        fauth = FirebaseAuth.getInstance();
        scene = new Scene(loadFXML("LoginScreen"));
        stage.setTitle("shadyAuto");
        scene.getStylesheets().add(getClass().getResource("/styling/dracula.css").toExternalForm());
        stage.setScene(scene);
        stage.setHeight(720);
        stage.setWidth(1280);
        stage.show();
        stage.setResizable(false);
    }

    public static void main(String[] args) {

        launch();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ShadyAuto.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    //main page test
    public class Main extends Application {
        double x,y = 0;
        @Override
        public void start(Stage primaryStage) throws Exception{
            Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            primaryStage.initStyle(StageStyle.UNDECORATED);

            root.setOnMousePressed(event -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });

            root.setOnMouseDragged(event -> {
                primaryStage.setX(event.getScreenX() - x);
                primaryStage.setY(event.getScreenY() - y);
            });

            primaryStage.setScene(new Scene(root, 1280, 720));
            primaryStage.show();
        }


        public static void main(String[] args) {
            launch(args);
        }
    }



}