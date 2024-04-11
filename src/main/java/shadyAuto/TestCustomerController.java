package shadyAuto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import shadyAuto.FirebaseControllers.VehicleController;
import shadyAuto.Models.Vehicle;

public class TestCustomerController {
    VehicleController vehicleController = new VehicleController();

    @FXML
    private Label loginLabel;

    @FXML
    void FetchVehicle(ActionEvent event) {
        Vehicle vehicle = vehicleController.GetByID("5173a9b2-666e-4855-8adc-e7012e824d75");
        if (vehicle != null) {
            System.out.println("Vehicle Found");
        } else {
            System.out.println("Vehicle not found");
        }
        Vehicle[] vehicles = vehicleController.GetByOwnerID("owner");
        if (vehicles != null) {
            for(Vehicle v : vehicles) {
                System.out.printf("Owner: %s, Make: %s, Model: %s, Year: %d\n", v.getOwner(), v.getMake(), v.getModel(), v.getYear());
            }
        } else {
            System.out.println("Vehicles not found");
        }
    }

    @FXML
    void createVehicle(ActionEvent event) {
       boolean created = vehicleController.Create("owner", "BNW", "m3", 2024);
         if (created) {
             System.out.println("Vehicle Created");
         } else {
             System.out.println("Vehicle not created");
         }
    }

}
