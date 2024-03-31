package org.example.demo1;

public class Parts {
    private String part;
    private double price;

    /*
        Constructors
     */
    public Parts(){
        part = "null";
        price = -1;
    }
    public Parts(String part, double price){
        this.part = part;
        this.price = price;
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

    /*
        Getters
     */

    public String getPart() {
        return part;
    }

    public double getPrice() {
        return price;
    }
}
