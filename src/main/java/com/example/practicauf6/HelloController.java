package com.example.practicauf6;

import Utilities.Actions;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        Actions.setConnection();
    }
}