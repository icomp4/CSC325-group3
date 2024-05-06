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
            ApiFuture<DocumentSnapshot> result = db.initialize().collection("customers").document(customerDetails).get();
            DocumentSnapshot document = result.get();
            if (document.exists()) {
                return new Customer(
                        document.getString("firstName"),
                        document.getString("lastName"),
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
    public boolean Create(Customer newCustomer) {
        {
            HashMap<String, Object> data = new HashMap<>();
            data.put("firstname", newCustomer.getFirstName());
            data.put("lastname", newCustomer.getLastName());
            data.put("phoneNumber", newCustomer.getPhoneNumber());
            data.put("customerID", newCustomer.getCustomerID());
            try {
                db.initialize().collection("customers").document(newCustomer.getCustomerID()).set(data);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

    public Customer GetByID(String customerID) {
        return getCustomer(customerID);
    }
    public shadyAuto.Models.Customer GetByName(String firsName, String lastName) {
        try{
            ApiFuture<QuerySnapshot> query = db.initialize().collection("customers").whereEqualTo("firstname", firsName).whereEqualTo("lastname", lastName).get();
            QuerySnapshot querySnapshot = query.get();
            if(querySnapshot.isEmpty()){
                return null;
            } else {
                return new shadyAuto.Models.Customer(

                        querySnapshot.getDocuments().getFirst().getString("firstName"),
                        querySnapshot.getDocuments().getFirst().getString("lastName"),
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
            DocumentReference docRef = db.initialize().collection("customers").document(customerID);
            docRef.delete();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public String GetIDFromName(String firstName, String lastName){
        try{
            ApiFuture<QuerySnapshot> query = db.initialize().collection("customers").whereEqualTo("firstname", firstName).whereEqualTo("lastname", lastName).get();
            QuerySnapshot querySnapshot = query.get();
            if(querySnapshot.isEmpty()){
                return null;
            } else {
                return querySnapshot.getDocuments().getFirst().getId();
            }
        } catch (Exception e){
            return null;
        }
    }
    public Customer[] GetAllCustomers() {
        try {
            ApiFuture<QuerySnapshot> query = db.initialize().collection("customers").get();
            QuerySnapshot querySnapshot = query.get();
            Customer[] customers = new Customer[querySnapshot.size()];
            for (int i = 0; i < querySnapshot.size(); i++) {
                customers[i] = new Customer(
                        querySnapshot.getDocuments().get(i).getString("customerID"),
                        querySnapshot.getDocuments().get(i).getString("firstname"),
                        querySnapshot.getDocuments().get(i).getString("lastname"),
                        querySnapshot.getDocuments().get(i).getString("phoneNumber")
                );
            }
            return customers;
        } catch (Exception e) {
            return null;
        }
    }

}
