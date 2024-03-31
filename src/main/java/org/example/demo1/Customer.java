package org.example.demo1;

public class Customer {
    private String name;
    private String address;

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
    public void setAddress(String address) {
        this.address = address;
    }

    /*
        Getters
    */

    public String getName(){
        return name;
    }

    public String getAddress() {
        return address;
    }


}
