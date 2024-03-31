package shadyAuto;

import java.util.ArrayList;

public class History {
    private int vehicleID;
    private ArrayList<Parts> partsOrder;
    private double price;
    private String date;

    /*
        Constructors
     */
    public History(){

    }

    public History(int vehicleID, ArrayList<Parts> partsOrder, double price, String date){
        this.vehicleID = vehicleID;
        this.partsOrder = partsOrder;
        this.price = price;
        this.date = date;
    }
    /*
        Setters
     */

    public void setVehicleID(int vehicleID) {
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

    public int getVehicleID() {
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
}