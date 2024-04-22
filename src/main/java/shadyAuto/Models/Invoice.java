package shadyAuto.Models;

import java.util.ArrayList;
import java.util.UUID;

public class Invoice {
    private String invoiceID;
    private String vehicleID;
    private String customerID;
    private ArrayList<Parts> partsOrder;
    private double price;
    private String date;

    /*
        Constructors
     */
    public Invoice(){

    }

    public Invoice(String invoiceID, String vehicleID, String customerID, ArrayList<Parts> partsOrder, double price, String date){
        this.invoiceID = invoiceID;
        this.vehicleID = vehicleID;
        this.customerID = customerID;
        this.partsOrder = partsOrder;
        price = 0;
        for(Parts part : partsOrder){
            price += part.getPrice();
        }
        this.price = price;
        this.date = date;
    }
    /*
        Setters
     */

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public void setPartsOrder(ArrayList<Parts> partsOrder) {
        this.partsOrder = partsOrder;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public ArrayList<Parts> getPartsOrder() {
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

}