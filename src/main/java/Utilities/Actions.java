package Utilities;

import Utilities.ConnectDB;

import java.io.BufferedReader;
import java.io.IOException;

import java.sql.*;
import java.util.ArrayList;

public final class Actions {
    public static ArrayList<String> databases = new ArrayList<>();
    public static ArrayList<String> tables = new ArrayList<>();

    private static Actions instance;

    private Actions(){}
    public static Actions getInstance() throws SQLException {
        if(instance == null) {
            instance = new Actions();
        }
        return instance;
    }
    public static void setConnection(){
        Connection con = null;
        // Construïm la query i la guardem en un String
//                String query = "SELECT film_id, title, description FROM sakila.film";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //prueba
        //boolean timeToQuit = false;
        /*
         * try-catch with resources: BufferedReader i Connection
         */

        try {
            con = ConnectDB.getInstance();
            //  La classe java.sql.ResultSet ens serveix per a guardar el resultat de l'execució de la sintaxi
            Statement stmt = null;
            String query = "SELECT DISTINCT TABLE_SCHEMA FROM TABLES";
            try {
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    databases.add(rs.getString("TABLE_SCHEMA"));

                }
/*                for (int i = 0; i < databases.size(); i++) {
                    System.out.println(databases.get(i));
                }//shows all tables inside */

                // Per a cada fila guardada dins del ResultSet, agafem les columnes que vulguem per a printar-les

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                if(stmt!=null)stmt.close();
            }
        }catch (SQLException ex) {
            try {
                throw new Exception("Error", ex);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            try {
                if(con != null){
                    con.close();
                    ConnectDB.setNull();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        }
    }

    public static void setTables (Object schema)
    {
        Connection con = null;
        String query = "SELECT DISTINCT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = '"+schema+"'";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try
        {
            con = ConnectDB.getInstance();
            Statement stmt = con.createStatement();
            //  La classe java.sql.ResultSet ens serveix per a guardar el resultat de l'execució de la sintaxi
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                tables.add(rs.getString("TABLE_NAME"));
            }
            for (int i = 0; i < tables.size(); i++) {
                System.out.println(tables.get(i));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(con != null){
                try {
                    con.close();
                    ConnectDB.setNull();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void showData (Object table, Object schema)
    {
        Connection con = null;
        String query = "SELECT DISTINCT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = '"+schema+"'";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try
        {
            con = ConnectDB.getInstance();
            Statement stmt = con.createStatement();
            //  La classe java.sql.ResultSet ens serveix per a guardar el resultat de l'execució de la sintaxi
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                tables.add(rs.getString("TABLE_NAME"));
            }
            for (int i = 0; i < tables.size(); i++) {
                System.out.println(tables.get(i));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(con != null){
                try {
                    con.close();
                    ConnectDB.setNull();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

/*    public ArrayList<String> loadSchemas() throws Exception {
        return databases;
    }*/
/*
    private static boolean executeMenu(Connection con) throws Exception {
        Character action;
>>>>>>> master
        int id;

        System.out.println("\n\n [L]ist | [C]ount | [I]nsert | [Q]uit: ");
        action = in.readLine();
        if ((action.length() == 0) || action.toUpperCase().charAt(0) == 'Q') {
            return true;
        }

        switch (action.toUpperCase().charAt(0)) {
            // Display a list (Read the records) of CLIENTE
            case 'L': {
                String query = "SELECT * FROM PRODUCTS";
                try (
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(query)) {
                    while (rs.next()) {
                        System.out.println(rs.getString("cod")
                                + "   " + rs.getString("name"));
                    }
                } catch (SQLException ex) {
                    throw new Exception("Error reading records table PRODUCTS", ex);
                }
                break;
            }

            case 'C': {
                String query = "SELECT COUNT(COD) FROM PRODUCTS";
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

<<<<<<< HEAD
=======
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
                break;
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
>>>>>>> master
        }

        return false;
    }
<<<<<<< HEAD
=======
    */

}
