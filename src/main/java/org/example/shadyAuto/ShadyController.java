package org.example.shadyAuto;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ShadyController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}