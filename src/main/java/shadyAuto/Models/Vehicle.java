package shadyAuto.Models;

public class Vehicle {
    private String owner;
    private String make;
    private String model;
    private int year;
    private String vehicleID;
    private String licensePlate;

    /*
        Constructors
    */
    public Vehicle(){

    }

    public Vehicle(String owner, String make, String model, int year, String vehicleID, String licensePlate) {
        this.owner = owner;
        this.make = make;
        this.model = model;
        this.year = year;
        this.vehicleID = vehicleID;
        this.licensePlate = licensePlate;
    }

    /*
        Setters
    */

    public void setOwner(String owner) {
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
    public void setLicensePlate(String licensePlate){
        this.licensePlate = licensePlate;
    }
    /*
        Getters
    */

    public String getOwner() {
        return owner;
    }
    public String getLicensePlate(){
        return this.licensePlate;
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

    public String getVehicleID() {
        return vehicleID;
    }
}
