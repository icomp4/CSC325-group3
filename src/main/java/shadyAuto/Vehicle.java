package shadyAuto;

public class Vehicle {
    private org.example.demo1.Customer owner;
    private String make;
    private String model;
    private int year;

    /*
        Constructors
    */
    public Vehicle(){

    }

    public Vehicle(org.example.demo1.Customer owner, String make, String model, int year){
        this.owner = owner;
        this.make = make;
        this.model = model;
        this.year = year;
    }

    /*
        Setters
    */

    public void setOwner(org.example.demo1.Customer owner) {
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

    public org.example.demo1.Customer getOwner() {
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
