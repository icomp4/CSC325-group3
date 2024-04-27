package shadyAuto.Models;

import java.util.ArrayList;

public class Invoice {
    private String invoiceID;
    private String vehicleID;
    private String customerID;
    private ArrayList<Part> partsOrder;
    private double price;
    private String date;

    /*
        Constructors
     */

    public Invoice(String invoiceID, String vehicleID, String customerID, ArrayList<Part> partsOrder, String date){
        this.invoiceID = invoiceID;
        this.vehicleID = vehicleID;
        this.customerID = customerID;
        this.partsOrder = partsOrder;
        this.price = calculatePrice(partsOrder);
        this.date = date;
    }
    /*
        Setters
     */


    public void setPartsOrder(ArrayList<Part> partsOrder) {
        this.partsOrder = partsOrder;
    }

    public void setDate(String date) {
        this.date = date;
    }

    /*
        Getters
     */

    public String getVehicleID() {
        return vehicleID;
    }

    public ArrayList<Part> getPartsOrder() {
        return partsOrder;
    }

    public double getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }
    public String getCustomerID() {
        return customerID;
    }
    public String getInvoiceID() {
        return invoiceID;
    }

    private double calculatePrice(ArrayList<Part> partsOrder){
        double price = 0;
        for(Part part : partsOrder){
            price += part.getPrice();
        }
        return price;
    }

}