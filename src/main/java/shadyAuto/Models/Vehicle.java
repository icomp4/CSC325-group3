package shadyAuto.Models;

public class Vehicle {
    private String ownerID;
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

    public Vehicle(String vehicleID,String ownerID, String make, String model, int year, String licensePlate) {
        this.vehicleID = vehicleID;
        this.ownerID = ownerID;
        this.make = make;
        this.model = model;
        this.year = year;

        this.licensePlate = licensePlate;
    }

    /*
        Setters
    */

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
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

    public String getOwnerID() {
        return ownerID;
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
