package shadyAuto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import shadyAuto.FirebaseControllers.CustomerController;

public class TestCustomerController {

    @FXML
    private TextField firstNameTXT;

    @FXML
    private TextField lastNameTXT;

    @FXML
    private Label loginLabel;

    @FXML
    private TextField phoneNumTXT;

    @FXML
    private TextField fullNameTXT;


    @FXML
    void CreateCustomer(ActionEvent event) {
        String firstName = this.firstNameTXT.getText();
        String lastName = this.lastNameTXT.getText();
        String phoneNum = this.phoneNumTXT.getText();
        if(firstName != "" && lastName != "" && phoneNum != "") {
            boolean customer = CustomerController.Create(firstName, lastName, phoneNum);
            if(customer) {
                loginLabel.setText("Customer Created");
                loginLabel.setTextFill(javafx.scene.paint.Color.GREEN);
            } else {
                loginLabel.setText("Customer Creation Failed");
                loginLabel.setTextFill(javafx.scene.paint.Color.RED);
            }
        }
    }
    @FXML
    void FetchCustomer(ActionEvent event) {
        String fullname = this.fullNameTXT.getText();
        shadyAuto.Models.Customer customer = CustomerController.GetByName(fullname);
        customer.Print();
    }
}
