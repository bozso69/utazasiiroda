package com.example.utazasiiroda;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GUI_basic_controller {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}