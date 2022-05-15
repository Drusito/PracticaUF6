package com.example.practicauf6;

import Utilities.Actions;
import Utilities.MySQLConnectionSettings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.*;
import javafx.scene.control.skin.TableColumnHeader;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class HelloController {
    @FXML
    private VBox background = new VBox();
    @FXML
    private Label welcomeText;
    @FXML
    private Button showData;
    @FXML
    private ComboBox comboSchema = new ComboBox();
    @FXML
    private ComboBox comboTables = null;
    @FXML
    protected void initialize() {
        try {
            setComboSchemas();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Método para rellenar el primer ComboBox con el nombre de las bases de datos
     * @return
     */
    private void setComboSchemas() throws Exception {
        for(String s : Actions.databases){
            comboSchema.getItems().add(s);
        }
    }
    /**
     * Método para comprobar que el primer desplegable haya sido rellenado, si no es así el segundo
     * no estará habilitado
     * @return
     */
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

    /**
     * Método para comprobar que el segundo desplegable haya sido rellenado, si no es así el botón
     * para obtener las filas no será habilitado
     * @return
     */
    @FXML
    protected void tableSelected(){
        if(comboTables.getValue() != null){
            showData.setDisable(false);
            MySQLConnectionSettings.setDatabase(comboSchema.getValue().toString());
        }
    }

    /**
     * Método para crear la tabla que aparecerá con la información de la base de datos seleccionada y rellenarla
     * con las cinco primeras filas.
     * @return
     */
    @FXML
    protected void setShowData() {
        if (background.getChildren().size() > 0) {
            background.getChildren().remove(0);
        }
        Actions.showData(comboTables.getValue());
        double porcentAncho = (double)100 / (double)Actions.getTablesColumnsName().size();
        double porcentAlto = (double)251 / (double)6;
        GridPane gp = new GridPane();
        for (int i = 0; i < Actions.getTablesColumnsName().size(); i++) {
            RowConstraints row = new RowConstraints();
            row.setMinHeight(porcentAlto);
            gp.getRowConstraints().add(row);
            ColumnConstraints col = new ColumnConstraints();
            col.setPercentWidth(porcentAncho);
            col.setHalignment(HPos.CENTER);
            gp.getColumnConstraints().add(col);
            Label labelTitle = new Label(Actions.getTablesColumnsName().get(i));
            labelTitle.setFont(new Font("Arial", 15));
            gp.add(labelTitle, i, 0);
        }
        int contador = 0;
        for (int i = 1; i < 6; i++) {
            RowConstraints row = new RowConstraints();
            row.setMinHeight(porcentAlto);
            gp.getRowConstraints().add(row);
            for (int j = 0; j < Actions.getTablesColumnsName().size(); j++) {
                try{
                    gp.add(new Label(Actions.getTablesColumnsData().get(contador)), j, i);
                }catch(Exception e){
                    System.out.println("NO HAY SUFICIENTES FILAS");
                    break;
                }
                contador++;
            }
        }
        background.getChildren().add(gp);
        showData.setDisable(true);
    }

    /**
     * Método para rellenar el segundo ComboBox con el nombre de las tablas contenidas en la base de datos
     * seleccionada
     * @return
     */

    private void setComboTables() throws Exception {
        comboTables.getItems().clear();
        for(String s : Actions.tables){
            comboTables.getItems().add(s);
        }
        tableSelected();
    }
}