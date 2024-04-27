package shadyAuto.FirebaseControllers;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import shadyAuto.Models.Invoice;
import shadyAuto.Models.Part;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvoiceController {
    private FirestoreDBConnection db;
    public InvoiceController(FirestoreDBConnection dbConnection) {
        this.db = dbConnection;
    }
    public boolean CreateInvoice(Invoice invoice){
        Map<String, Object> invoiceMap = new HashMap<>();
        invoiceMap.put("invoiceID", invoice.getInvoiceID());
        invoiceMap.put("vehicleID", invoice.getVehicleID());
        invoiceMap.put("customerID", invoice.getCustomerID());

        List<Map<String, Object>> partsList = new ArrayList<>();
        for (Part part : invoice.getPartsOrder()) {
            Map<String, Object> partMap = new HashMap<>();
            partMap.put("partID", part.getPartID());
            partMap.put("name", part.getName());
            partMap.put("price", part.getPrice());
            partsList.add(partMap);
        }

        invoiceMap.put("partsOrder", partsList);
        invoiceMap.put("price", invoice.getPrice());
        invoiceMap.put("date", invoice.getDate());

        try {
            db.initialize().collection("invoices").document(invoice.getInvoiceID()).set(invoiceMap);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public Invoice GetInvoice(String invoiceID){
        try {
            Map<String, Object> invoiceMap = db.initialize().collection("invoices").document(invoiceID).get().get().getData();
            String vehicleID = (String) invoiceMap.get("vehicleID");
            String customerID = (String) invoiceMap.get("customerID");
            double price = (double) invoiceMap.get("price");
            String date = (String) invoiceMap.get("date");

            List<Map<String, Object>> partsList = (List<Map<String, Object>>) invoiceMap.get("partsOrder");
            ArrayList<Part> partsOrder = new ArrayList<>();
            for (Map<String, Object> partMap : partsList) {
                String partID = (String) partMap.get("partID");
                String name = (String) partMap.get("name");
                double partPrice = (double) partMap.get("price");
                Part part = new Part(partID, name, partPrice);
                partsOrder.add(part);
            }

            return new Invoice(invoiceID, vehicleID, customerID, partsOrder, date);
        } catch (Exception e) {
            return null;
        }
    }
    public Invoice[] GetAllInvoices(){
        try {
            List<QueryDocumentSnapshot> documents = db.initialize().collection("invoices").get().get().getDocuments();
            Invoice[] invoices = new Invoice[documents.size()];
            for (int i = 0; i < documents.size(); i++) {
                Map<String, Object> invoiceMap = documents.get(i).getData();
                String invoiceID = documents.get(i).getId();
                String vehicleID = (String) invoiceMap.get("vehicleID");
                String customerID = (String) invoiceMap.get("customerID");
                double price = (double) invoiceMap.get("price");
                String date = (String) invoiceMap.get("date");

                List<Map<String, Object>> partsList = (List<Map<String, Object>>) invoiceMap.get("partsOrder");
                ArrayList<Part> partsOrder = new ArrayList<>();
                for (Map<String, Object> partMap : partsList) {
                    String partID = (String) partMap.get("partID");
                    String name = (String) partMap.get("name");
                    double partPrice = (double) partMap.get("price");
                    Part part = new Part(partID, name, partPrice);
                    partsOrder.add(part);
                }

                invoices[i] = new Invoice(invoiceID, vehicleID, customerID, partsOrder, date);
            }
            return invoices;
        } catch (Exception e) {
            return null;
        }
    }

}
