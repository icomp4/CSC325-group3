package org.example.demo1;

public class Customer {
    private String name;
    private String phoneNumber;

    /*
        Constructors
    */

    public Customer(){
        name = "null";
    }
    public Customer(String name){
        this.name = name;
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

    /*
        Getters
    */

    public String getName(){
        return name;
    }

    public String getphoneNumber() {
        return phoneNumber;
    }


}
