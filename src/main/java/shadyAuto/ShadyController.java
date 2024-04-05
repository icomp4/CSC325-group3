package shadyAuto;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import shadyAuto.FirebaseControllers.User;

public class ShadyController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
       User.Login("TestUser", "password");
    }
}