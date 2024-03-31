package shadyAuto.Models;

/*
        Employee class contains basic information that ShadyAutos employees must have.
 */
public class Employee {

    private String employeeID;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private double salary;

    /*
        Constructors
    */
    public Employee(){
        employeeID = "null";
        userName = "null";
        password = "null";
        firstName = "null";
        lastName = "null";
        salary = 0;
    }
    public Employee(String employeeID, String userName, String password, String firstName, String lastName, double salary){
        this.employeeID = employeeID;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    /*
        Setters
    */

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /*
        Getters
    */

    public String getEmployeeID() {
        return employeeID;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getSalary() {
        return salary;
    }

    public String getUserName() {
        return userName;
    }
}
