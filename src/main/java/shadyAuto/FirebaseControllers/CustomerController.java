package shadyAuto.FirebaseControllers;

import com.google.api.core.ApiFunction;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import shadyAuto.Models.Customer;

import java.util.HashMap;
import java.util.UUID;

/*
    This class is responsible for handling all the customer related operations in the database
    Create -> Create a new customer with the given details (First Name, Last Name, Phone)
    Get -> Get the customer details by customerID
    GetByName -> Get the customer details by name
 */
public class CustomerController {
    // Top level database connection, so it can be reused by all functions
    private final FirestoreDBConnection db;
    public CustomerController(FirestoreDBConnection db) {
        this.db = db;
    }

    /*
        This function is a private helper method, it accepts a customerID, or a name and returns the customer details
        It is useful to negate rewriting the same code in multiple functions
     */
    private Customer getCustomer(String customerDetails) {
        try {
            ApiFuture<DocumentSnapshot> result = db.initialize().collection("Customers").document(customerDetails).get();
            DocumentSnapshot document = result.get();
            if (document.exists()) {
                return new Customer(
                        document.getString("name"),
                        document.getString("phoneNumber"),
                        document.getString("customerID")
                );
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
    public boolean Create(String FirstName, String LastName, String Phone) {
        UUID id = UUID.randomUUID();
        shadyAuto.Models.Customer newCustomer = new shadyAuto.Models.Customer(FirstName + " "  + LastName, Phone, id.toString());
        HashMap<String, Object> data = new HashMap<>();
        data.put("name", newCustomer.getName());
        data.put("phoneNumber", newCustomer.getphoneNumber());
        data.put("customerID", newCustomer.getCustomerID());
        try {
            db.initialize().collection("Customers").document(id.toString()).set(data);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public shadyAuto.Models.Customer GetByID(String customerID) {
        return getCustomer(customerID);
    }
    public shadyAuto.Models.Customer GetByName(String name) {
        try{
            ApiFuture<QuerySnapshot> query = db.initialize().collection("Customers").whereEqualTo("name", name).get();
            QuerySnapshot querySnapshot = query.get();
            if(querySnapshot.isEmpty()){
                return null;
            } else {
                return new shadyAuto.Models.Customer(
                        querySnapshot.getDocuments().getFirst().getString("name"),
                        querySnapshot.getDocuments().getFirst().getString("phoneNumber"),
                        querySnapshot.getDocuments().getFirst().getString("customerID")
                );
            }
        } catch (Exception e){
            return null;
        }
    }
    public boolean Delete(String customerID) {
        try {
            DocumentReference docRef = db.initialize().collection("Customers").document(customerID);
            docRef.delete();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
