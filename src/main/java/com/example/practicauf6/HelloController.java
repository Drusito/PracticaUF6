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
    private Button showData;
    @FXML
    private ComboBox comboSchema = new ComboBox();
    @FXML
    private ComboBox comboTables = new ComboBox();

    @FXML
    protected void initialize(){

        try {
            setComboSchemas();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void setComboSchemas() throws Exception {
        for(String s : Actions.databases){
            comboSchema.getItems().add(s);
        }
    }
    @FXML
    protected void schemaSelected() {
        if(comboSchema.getValue() != null) {
            comboTables.setDisable(false);
            Actions.setTables(comboSchema.getValue());
            try {
                setComboTables();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    @FXML
    protected void tableSelected(){
        if(comboTables.getValue() != null){
            showData.setDisable(false);
            MySQLConnectionSettings.database = comboSchema.getValue().toString();
        }
    }

    protected void setComboTables() throws Exception {
        for(String s : Actions.tables){
            comboTables.getItems().add(s);
        }
        tableSelected();
    }

}