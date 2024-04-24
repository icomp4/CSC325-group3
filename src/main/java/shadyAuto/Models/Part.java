package shadyAuto.Models;

public class Part {
    private String partID;
    private String name;
    private double price;

    /*
        Constructor
     */

    public Part(String partID, String name, double price){
        this.partID = partID;
        this.name = name;
        this.price = price;
    }

    public String getPartID() {
        return partID;
    }

    public void setPartID(String partID) {
        this.partID = partID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
