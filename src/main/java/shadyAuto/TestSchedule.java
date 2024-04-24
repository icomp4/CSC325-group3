package shadyAuto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import shadyAuto.FirebaseControllers.VehicleController;
import shadyAuto.Models.Vehicle;

import java.util.UUID;

public class TestSchedule {
    VehicleController vehicleController = new VehicleController(ShadyAuto.db);

    @FXML
    private Label loginLabel;

    @FXML
    void createSchedule(ActionEvent event) {
        VehicleController vehicleController = new VehicleController(ShadyAuto.db);
        String ownerID = String.valueOf(UUID.randomUUID());
        String vehicleID = String.valueOf(UUID.randomUUID());
        Vehicle vehicle = new Vehicle(ownerID, "BMW", "M4", 2024, vehicleID, "123ABC");
        boolean created = vehicleController.Create(vehicle);
        if (created) {
            System.out.println("Vehicle created");
        } else {
            System.out.println("Vehicle not created");
        }
    }

    @FXML
    void deleteSchedule(ActionEvent event) {
        boolean deleted = vehicleController.DeleteByLicense("123ABC");
        if (deleted) {
            System.out.println("Vehicle deleted");
        } else {
            System.out.println("Vehicle not deleted");
        }
    }

    @FXML
    void getSchedule(ActionEvent event) {
        Vehicle bmw = vehicleController.GetByLicense("123ABC");
        System.out.printf("Owner: %s\nMake: %s\nModel: %s\nYear: %d\nVehicle ID: %s\nLicense Plate: %s\n",
                bmw.getOwner(), bmw.getMake(), bmw.getModel(), bmw.getYear(), bmw.getVehicleID(), bmw.getLicensePlate());
    }
    @FXML
    void getAll(ActionEvent event) {
    }

    @FXML
    void updateSchedule(ActionEvent event) {
    }

}
