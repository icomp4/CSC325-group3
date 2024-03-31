package shadyAuto.Models;

import shadyAuto.Models.Customer;

public class Vehicle {
    private Customer owner;
    private String make;
    private String model;
    private int year;

    /*
        Constructors
    */
    public Vehicle(){

    }

    public Vehicle(Customer owner, String make, String model, int year){
        this.owner = owner;
        this.make = make;
        this.model = model;
        this.year = year;
    }

    /*
        Setters
    */

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(int year) {
        this.year = year;
    }

    /*
        Getters
    */

    public Customer getOwner() {
        return owner;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }
}
