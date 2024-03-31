package org.example.shadyAuto;

public class Customer {
    private String name;
    private String phoneNumber;
    private int customerID;

    /*
        Constructors
    */

    public Customer(){
        name = "null";
    }
    public Customer(String name, String phoneNumber, int customerID){
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

    public void setCustomerID(int customerID) {
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

    public int getCustomerID() {
        return customerID;
    }
}
