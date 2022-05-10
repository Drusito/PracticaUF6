package com.example.practicauf6;

import Utilities.Actions;
import Utilities.ConnectDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Button connect;
    @FXML
    private ComboBox schemas = new ComboBox();
    @FXML
    private ComboBox tables = new ComboBox();

    @FXML
    protected void initialize(){
        for(String s : Actions.databases){
            System.out.println(s);
            schemas.getItems().add(s);
        }
//        System.out.println(schemas.getValue());
    }
    @FXML
    protected void onHelloButtonClick() {
        connect.setDisable(true);
    }
    @FXML
    private void comboAction(ActionEvent event) {

        System.out.println(schemas.getValue());

    }
    protected void setSchemas() throws Exception {
        for(String s : Actions.databases){
            System.out.println(s);
            schemas.getItems().add(s);
        }
    }
    @FXML
    protected void schemaSelected() {
        if(schemas.getValue() != null) {
            tables.setDisable(false);
        }
    }
}