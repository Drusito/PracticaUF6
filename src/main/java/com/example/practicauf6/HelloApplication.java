package com.example.practicauf6;

import Utilities.Actions;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Actions.setConnection();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 500);
        stage.setTitle("DBMS_Checker!");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "", ButtonType.CLOSE);
            a.show();
            a.setTitle("Tancant l'aplicació");
            a.setHeaderText("Gràcies per usar la nostra aplicació");
            stage.close();
        });
    }
    public static void main(String[] args) {
        launch();
    }
}