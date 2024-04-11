package shadyAuto.FirebaseControllers;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import shadyAuto.Models.Vehicle;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class VehicleController {
    // Top level database connection, so it can be reused by all functions
    static FirestoreDBConnection db = new FirestoreDBConnection();

    public boolean Create(String ownerID, String make, String model, int year) {
        UUID id = UUID.randomUUID();
        Vehicle newVehicle = new Vehicle(ownerID, make, model, year, id.toString());
        HashMap<String, Object> data = new HashMap<>();
        data.put("owner", newVehicle.getOwner());
        data.put("make", newVehicle.getMake());
        data.put("model", newVehicle.getModel());
        data.put("year", newVehicle.getYear());
        data.put("vehicleID", newVehicle.getVehicleID());
        data.put("ownerID", ownerID);
        try {
            db.initialize().collection("Vehicles").document(id.toString()).set(data);
            return true;
        } catch (Exception e) {
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
                        document.getString("vehicleID")
                );
            } else {
                return null;
            }
        } catch (Exception e) {
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
                        documents.getDocuments().get(i).getString("vehicleID")
                );
            }
            return vehicles;
        } catch (Exception e) {
            return null;
        }
    }
    public boolean Delete(String vehicleID) {
        try {
            db.initialize().collection("Vehicles").document(vehicleID).delete();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public Vehicle GetByID(String vehicleID) {
        return getVehicle(vehicleID);
    }
}
