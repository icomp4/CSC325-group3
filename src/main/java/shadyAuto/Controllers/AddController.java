package shadyAuto.Controllers;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import shadyAuto.FirebaseControllers.CustomerController;
import shadyAuto.FirebaseControllers.UserController;
import shadyAuto.FirebaseControllers.VehicleController;
import shadyAuto.LoginScreen;
import shadyAuto.Models.Customer;
import shadyAuto.Models.Vehicle;
import shadyAuto.ShadyAuto;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class AddController implements Initializable {
    CustomerController customerController = new CustomerController(ShadyAuto.db);
    VehicleController vehicleController = new VehicleController(ShadyAuto.db);
    private UserController userController;

    @FXML
    private ImageView Exit;

    @FXML
    private Label Menu;

    @FXML
    private Label MenuClose;

    @FXML
    private TextField fnameTxt;

    @FXML
    private TextField lnameTxt;

    @FXML
    private TextField makeTxt;

    @FXML
    private TextField modelTxt1;

    @FXML
    private TextField ownerNameTxt;

    @FXML
    private TextField phoneTxt;

    @FXML
    private TextField plateTxt;

    @FXML
    private AnchorPane slider;

    @FXML
    private TextField yearTxt;

    @FXML
    private Button scheduleBuilderBtn;


    @FXML
    void OpenSchedule(ActionEvent event) {
        try {
            ShadyAuto.setRoot("employee-schedule");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    void AddCustomer(ActionEvent event) {
        Customer customer = new Customer(UUID.randomUUID().toString(), fnameTxt.getText(), lnameTxt.getText(), phoneTxt.getText());
        boolean created = customerController.Create(customer);
        if(created) {
            System.out.println("Customer Created");
        } else {
            System.out.println("Customer not created");
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
    void AddVehicle(ActionEvent event) {
        String fullName = ownerNameTxt.getText();
        String[] names = fullName.split(" ");
        String ownerID = customerController.GetIDFromName(names[0], names[1]);
        Vehicle vehicle = new Vehicle( UUID.randomUUID().toString(), ownerID, makeTxt.getText(), modelTxt1.getText(), Integer.parseInt(yearTxt.getText()), plateTxt.getText());
        boolean created = vehicleController.Create(vehicle);
        if(created) {
            System.out.println("Vehicle Created");
        } else {
            System.out.println("Vehicle not created");
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
    @FXML
    void Logout(ActionEvent event) {
        try {
            ShadyAuto.setRoot("LoginScreen");
            LoginScreen.currentUser = "";
            LoginScreen.employeeName = "";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        userController = new UserController(ShadyAuto.db);
        String currentUsername = LoginScreen.currentUser;
        boolean isManager = userController.getIsManagerStatus(currentUsername);
        scheduleBuilderBtn.setVisible(isManager);
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
    }
}
