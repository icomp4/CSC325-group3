package shadyAuto.Controllers;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import shadyAuto.FirebaseControllers.FirestoreDBConnection;
import shadyAuto.FirebaseControllers.UserController;
import shadyAuto.LoginScreen;
import shadyAuto.ShadyAuto;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private ImageView Exit;

    @FXML
    private Label Menu;

    @FXML
    private Label MenuClose;

    @FXML
    private Label nameTxt;

    @FXML
    private AnchorPane slider;
    @FXML
    private Button scheduleBuilderBtn;
    private UserController userController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        userController = new UserController(ShadyAuto.db);

        // Example username retrieved from the login session
        String currentUsername = LoginScreen.currentUser;
        System.out.println(currentUsername);

        // Check if the user is a manager and modify the button's visibility
        boolean isManager = userController.getIsManagerStatus(currentUsername);
        scheduleBuilderBtn.setVisible(isManager);
        Exit.setOnMouseClicked(event -> {
            System.exit(0);
        });
        slider.setTranslateX(0); //-176 to show again
        Menu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(0);
            slide.play();

            slider.setTranslateX(-176);

            slide.setOnFinished((ActionEvent e)-> {
                Menu.setVisible(false);
                MenuClose.setVisible(true);
            });
        });

        MenuClose.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(-176);
            slide.play();

            slider.setTranslateX(0);

            slide.setOnFinished((ActionEvent e)-> {
                Menu.setVisible(true);
                MenuClose.setVisible(false);
            });
        });
        nameTxt.setText("Welcome Back " + LoginScreen.employeeName + "!");

    }
    @FXML
    void OpenScheduleBuilder(ActionEvent event) {
        try {
            ShadyAuto.setRoot("schedule-builder");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void OpenAddScreen(ActionEvent event) {
        try {
            ShadyAuto.setRoot("AddScreen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void OpenDashboard(ActionEvent event) {
        try {
            ShadyAuto.setRoot("MainScreen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void OpenEmployeesSchedule(ActionEvent event) {
        try {
            ShadyAuto.setRoot("employee-schedule");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void OpenHistory(ActionEvent event) {
        try {
            ShadyAuto.setRoot("HistoryScreen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
