package shadyAuto.FirebaseControllers;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import shadyAuto.Models.Vehicle;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Logger;


public class VehicleController {
    // Top level database connection, so it can be reused by all functions
    private final FirestoreDBConnection db;
    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());
    public VehicleController(FirestoreDBConnection db) {
        this.db = db;
    }
    public boolean Create(Vehicle newVehicle){
        HashMap<String, Object> data = new HashMap<>();
        data.put("vehicleID", newVehicle.getVehicleID());
        data.put("owner", newVehicle.getOwner());
        data.put("make", newVehicle.getMake());
        data.put("model", newVehicle.getModel());
        data.put("year", newVehicle.getYear());
        data.put("licensePlate", newVehicle.getLicensePlate());
        try {
            db.initialize().collection("Vehicles").document(newVehicle.getVehicleID()).set(data);
            return true;
        } catch (Exception e) {
            LOGGER.log(LOGGER.getLevel(), "Error during CreateVehicle", e);
            return false;
        }
    }
    private Vehicle getVehicle(String vehicleDetails) {
        try {
            ApiFuture<DocumentSnapshot> result = db.initialize().collection("Vehicles").document(vehicleDetails).get();
            DocumentSnapshot document = result.get();
            if (document.exists()) {
                return new Vehicle(
                        document.getString("owner"),
                        document.getString("make"),
                        document.getString("model"),
                        Objects.requireNonNull(document.getLong("year")).intValue(),
                        document.getString("vehicleID"),
                        document.getString("licensePlate")
                );
            } else {
                LOGGER.log(LOGGER.getLevel(), "Vehicle not found: " + vehicleDetails);
                return null;
            }
        } catch (Exception e) {
            LOGGER.log(LOGGER.getLevel(), "Error during GetVehicle", e);
            return null;
        }
    }
    public Vehicle[] GetByOwnerID(String ownerID) {
        try {
            ApiFuture<QuerySnapshot> result = db.initialize().collection("Vehicles").whereEqualTo("ownerID", ownerID).get();
            QuerySnapshot documents = result.get();
            Vehicle[] vehicles = new Vehicle[documents.size()];
            for (int i = 0; i < documents.size(); i++) {
                vehicles[i] = new Vehicle(
                        documents.getDocuments().get(i).getString("owner"),
                        documents.getDocuments().get(i).getString("make"),
                        documents.getDocuments().get(i).getString("model"),
                        Objects.requireNonNull(documents.getDocuments().get(i).getLong("year")).intValue(),
                        documents.getDocuments().get(i).getString("vehicleID"),
                        documents.getDocuments().get(i).getString("licensePlate")
                );
            }
            return vehicles;
        } catch (Exception e) {
            LOGGER.log(LOGGER.getLevel(), "Error during GetByOwnerID", e);
            return null;
        }
    }
    public boolean Delete(String vehicleID) {
        try {
            db.initialize().collection("Vehicles").document(vehicleID).delete();
            return true;
        } catch (Exception e) {
            LOGGER.log(LOGGER.getLevel(), "Error during DeleteVehicle", e);
            return false;
        }
    }
    public Vehicle GetByID(String vehicleID) {
        return getVehicle(vehicleID);
    }
    public Vehicle GetByLicense(String license){
        try {
            ApiFuture<QuerySnapshot> result = db.initialize().collection("Vehicles").whereEqualTo("licensePlate", license).get();
            QuerySnapshot documents = result.get();
            if(!documents.isEmpty()){
                return new Vehicle(
                        documents.getDocuments().getFirst().getString("owner"),
                        documents.getDocuments().getFirst().getString("make"),
                        documents.getDocuments().getFirst().getString("model"),
                        Objects.requireNonNull(documents.getDocuments().getFirst().getLong("year")).intValue(),
                        documents.getDocuments().getFirst().getString("vehicleID"),
                        documents.getDocuments().getFirst().getString("licensePlate")
                );
            } else {
                LOGGER.log(LOGGER.getLevel(), "Vehicle not found: " + license);
                return null;
            }
        } catch (Exception e) {
            LOGGER.log(LOGGER.getLevel(), "Error during GetVehicle", e);
            return null;
        }
    }
    public boolean DeleteByLicense(String license){
        try {
            ApiFuture<QuerySnapshot> result = db.initialize().collection("Vehicles").whereEqualTo("licensePlate", license).get();
            QuerySnapshot documents = result.get();
            if(!documents.isEmpty()){
                db.initialize().collection("Vehicles").document(documents.getDocuments().getFirst().getId()).delete();
                return true;
            } else {
                LOGGER.log(LOGGER.getLevel(), "Vehicle not found: " + license);
                return false;
            }
        } catch (Exception e) {
            LOGGER.log(LOGGER.getLevel(), "Error during DeleteVehicle", e);
            return false;
        }
    }

}


