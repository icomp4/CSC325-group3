package shadyAuto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import shadyAuto.FirebaseControllers.User;

public class ShadyController {

    @FXML
    private TextField passwordText;

    @FXML
    private TextField usernameText;
    @FXML
    private Label loginLabel;

    @FXML
    void Login(ActionEvent event) {
        boolean loggedIn = User.Login(usernameText.getText(), passwordText.getText());
        if(loggedIn){
            loginLabel.setText("Login Successful");
            loginLabel.setTextFill(javafx.scene.paint.Color.GREEN);
        }
        else{
            loginLabel.setText("Login Failed");
            loginLabel.setTextFill(javafx.scene.paint.Color.RED);
        }
    }

}
