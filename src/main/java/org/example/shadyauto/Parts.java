package org.example.shadyauto;

public class Parts {
    private String part;
    private double price;
    private int partID;

    /*
        Constructors
     */
    public Parts(){
        part = "null";
        price = -1;
    }
    public Parts(String part, double price, int partID){
        this.part = part;
        this.price = price;
        this.partID = partID;
    }

    /*
        Setters
     */

    public void setPart(String part) {
        this.part = part;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPartID(int partID) {
        this.partID = partID;
    }
/*
        Getters
     */

    public String getPart() {
        return part;
    }

    public double getPrice() {
        return price;
    }

    public int getPartID() {
        return partID;
    }
}
