package shadyAuto.Models;

public class Customer {
    private String name;
    private String phoneNumber;
    private String customerID;

    /*
        Constructors
    */

    public Customer(){
        name = "null";
    }
    public Customer(String name, String phoneNumber, String customerID){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.customerID = customerID;
    }

    /*
        Setters
    */

    public void setName(String name){
        this.name = name;
    }
    public void setphoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }
/*
        Getters
    */

    public String getName(){
        return name;
    }

    public String getphoneNumber() {
        return phoneNumber;
    }

    public String getCustomerID() {
        return customerID;
    }
    public void Print(){
        System.out.println("Name: " + name);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Customer ID: " + customerID);
    }
}
