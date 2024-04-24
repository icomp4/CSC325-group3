package shadyAuto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import shadyAuto.FirebaseControllers.CustomerController;
import shadyAuto.FirebaseControllers.VehicleController;
import shadyAuto.Models.Customer;
import shadyAuto.Models.Vehicle;

import java.util.UUID;

public class AddController {
    CustomerController customerController = new CustomerController(ShadyAuto.db);
    VehicleController vehicleController = new VehicleController(ShadyAuto.db);

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
    void OpenSchedule(ActionEvent event) {
        try {
            ShadyAuto.setRoot("Schedule-builder");
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
        Vehicle vehicle = new Vehicle(ownerID, makeTxt.getText(), modelTxt1.getText(), Integer.parseInt(yearTxt.getText()), UUID.randomUUID().toString(), plateTxt.getText());
        boolean created = vehicleController.Create(vehicle);
        if(created) {
            System.out.println("Vehicle Created");
        } else {
            System.out.println("Vehicle not created");
        }
    }

}