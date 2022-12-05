package jeu2048;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class testControler {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
