package shadyAuto.Models;

public class Customer {
    private String customerID;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    /*
        Constructors
    */

    public Customer(){
        firstName = "null";
        lastName = "null";
    }
    public Customer(String customerID, String firstName, String lastName, String phoneNumber){
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    /*
        Setters
    */

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }
    /*
        Getters
    */

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCustomerID() {
        return customerID;
    }
    public void Print(){
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Customer ID: " + customerID);
    }
}
