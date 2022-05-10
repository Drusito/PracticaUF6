package com.example.practicauf6;

import Utilities.Actions;
import Utilities.MySQLConnectionSettings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Button connect;
    @FXML

    private ComboBox comboSchema = new ComboBox();
    @FXML
    private ComboBox comboTables = new ComboBox();
// a
    @FXML
    protected void initialize(){
        for(String s : Actions.databases){
            System.out.println(s);
            comboSchema.getItems().add(s);
        }
//        System.out.println(schemas.getValue());
    }
    @FXML
    protected void onHelloButtonClick() {
        connect.setDisable(true);
    }
    protected void setComboSchemas() throws Exception {
        for(String s : Actions.databases){
            comboSchema.getItems().add(s);

        }
    }
    @FXML
    protected void schemaSelected() {
        if(comboSchema.getValue() != null) {
            System.out.println(comboSchema.getValue());
            comboTables.setDisable(false);
            Actions.setTables(comboSchema.getValue());
            System.out.println(Actions.tables.size());
            for(String s : Actions.tables){
                System.out.println(s);
                System.out.println(comboSchema.getValue());
                comboTables.getItems().add(s);
            }
            System.out.println(comboSchema.getValue());

        }
    }
    @FXML
    protected void tableSelected(){
        if(comboTables.getValue() != null){
            showData.setDisable(false);
        }
    }


    protected void setComboTables() throws Exception {
        for(String s : Actions.databases){
            System.out.println(s);
            comboSchema.getItems().add(s);
        }
    }

}