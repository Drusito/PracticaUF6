package com.example.practicauf6;

import Utilities.Utilities;
import Utilities.ConnectionDB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        boolean timeToQuit = false;
        /*
         * try-catch with resources: Connection
         */
        try (Connection con = ConnectionDB.getInstance()) {
            do {
                timeToQuit = executeMenu(con);
            } while (!timeToQuit);

        } catch (IOException e) {
            System.out.println("Error " + e.getClass().getName() + " , quiting.");
            System.out.println("Message: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error closing resource " + e.getClass().getName());
            System.out.println("Message: " + e.getMessage());
        } finally {
            try {
                ConnectionDB.closeConnection();
            } catch (SQLException e) {
                System.out.println("Error closing resource " + e.getClass().getName());
            }
        }

    }

    private boolean executeMenu(Connection con) throws Exception {
        Character action;
        int id;

        action = Utilities.leerChar("\n\n [L]ist | [C]ount | [I]nsert | [U]pdate | [Q]uit: ");
        action = Character.toUpperCase(action);
        if (action == 'Q') {
            return true;
        }

        switch (action) {
            // Mostrar els detalls de totes les pelis
            case 'L': {
                // Construïm la query i la guardem en un String
                String query = "SELECT film_id, title, description FROM sakila.film";

                // try-catch with resources: Statement i ResultSet
                try (
                        // Creem un java.sql.Statement executable (Similar al PREPARE STATEMENT FROM stmt)
                        Statement stmt = con.createStatement();
                        // La classe java.sql.ResultSet ens serveix per a guardar el resultat de l'execució de la sintaxi
                        ResultSet rs = stmt.executeQuery(query)) {

                    // Per a cada fila guardada dins del ResultSet, agafem les columnes que vulguem per a printar-les
                    while (rs.next()) {
                        System.out.println(rs.getString("film_id")
                                + "   " + rs.getString("title")
                                + "   " + rs.getString("description")
                        );
                    }
                } catch (SQLException ex) {
                    throw new Exception("Error reading records table FILMS", ex);
                }
                break;
            }

            case 'C': {
                // TODO: Fer codi que mostri el recompte de pelis de la BD
                System.out.print("Numero de Peliculas: ");
                String query = "SELECT COUNT(film_id) FROM sakila.film";
                try (
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(query)) {
                    if (rs.next()) {
                        System.out.println(rs.getInt(1));
                    }
                } catch (SQLException ex) {
                    throw new Exception("Error reading records table PRODUCT", ex);
                }
                break;
            }

            case 'I': {
                // TODO: Fer codi que permeti inserir noves pelis
/*                String title = Utilities.llegirParaula("Cual es el titulo?");
                String description = Utilities.llegirFrase("Cual es la descripcion de la pelicula");
                int languageId = Utilities.llegirInt("Cual es el Language ID?",1, 7);
                String query = "INSERT INTO sakila.film (title, description, language_id) VALUES ( '"+title+"', '"+description+"', "+languageId+") > ?";
                try (
                        Statement stmt = con.createStatement()) {
                        stmt.executeUpdate(query);
                } catch (SQLException ex) {
                    throw new Exception("Error reading records on table PRODUCTS:" + ex.getMessage(), ex);
                }
                break;*/
                String title = Utilities.leerString("Cual es el titulo?");
                String description = Utilities.leerString("Cual es la descripcion de la pelicula");
                int languageId = Utilities.leerIntLimites("Cual es el Language ID?",1, 7);
                PreparedStatement pStmt = null;
                String query = "INSERT INTO sakila.film (title, description, language_id) VALUES ( ?, ? , ?) ";
                try {
                    pStmt = con.prepareStatement(query);
                    pStmt.setString(1, title);
                    pStmt.setString(2, description);
                    pStmt.setInt(3, languageId);
                    pStmt.executeUpdate();
                } catch (SQLException ex) {
                    throw new Exception("Error reading records on table PRODUCTS:" + ex.getMessage(), ex);
                }
                break;

            }

            case 'U': {
                int filmId = Utilities.leerIntLimites("Cual es el Film ID de la pelicula que quieres cambiar?",1, 1000000);
                String title = Utilities.leerString("Por qué titulo quieres cambiarlo?");
                String description = Utilities.leerString("Por qué descripcion quieres cambiarlo?");
                int languageId = Utilities.leerIntLimites("Por qué language ID quieres cambiarlo?",1, 7);
                PreparedStatement pStmt = null;
                String query = "UPDATE sakila.film SET title = ?, description = ?, language_id = ? WHERE film_id = ?";
                try {
                    pStmt = con.prepareStatement(query);
                    pStmt.setString(1, title);
                    pStmt.setString(2, description);
                    pStmt.setInt(3, languageId);
                    pStmt.setInt(4, filmId);
                    pStmt.executeUpdate();
                } catch (SQLException ex) {
                    throw new Exception("Error reading records on table PRODUCTS:" + ex.getMessage(), ex);
                }
                break;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        launch();
    }
}