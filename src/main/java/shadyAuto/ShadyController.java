package shadyAuto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import shadyAuto.FirebaseControllers.UserController;
import javafx.fxml.Initializable;

import java.io.IOException;
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
    void Login(ActionEvent event) throws IOException {
        String usernameTxt = this.usernameTxt.getText();
        String pwTxt = this.pwTxt.getText();
        UserController userController = new UserController(LoginController.db);
        if(usernameTxt != "" && pwTxt != "") {
            boolean login = userController.Login(usernameTxt, pwTxt);
            if(login) {
                LoginController.setRoot("MainScreen");

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
