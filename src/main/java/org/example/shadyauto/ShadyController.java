package org.example.shadyauto;

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