package shadyAuto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import shadyAuto.FirebaseControllers.User;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ShadyController implements Initializable {

    @FXML
    private Pane Background;

    @FXML
    private Label errorLbl;

    @FXML
    private TextField pwTxt;

    @FXML
    private TextField usernameTxt;

    @FXML
    void Login(ActionEvent event) {
        String usernameTxt = this.usernameTxt.getText();
        String pwTxt = this.pwTxt.getText();
        if(usernameTxt != "" && pwTxt != "") {
            boolean login = User.Login(usernameTxt, pwTxt);
            if(login) {
                errorLbl.setText("Login Successful");
                errorLbl.setTextFill(javafx.scene.paint.Color.GREEN);
            } else {
                errorLbl.setText("Login Failed");
                errorLbl.setTextFill(javafx.scene.paint.Color.RED);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameTxt.setStyle("-fx-background-color: white;");
        pwTxt.setStyle("-fx-background-color: white;");
    }

}
