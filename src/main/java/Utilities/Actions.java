package Utilities;

import Utilities.ConnectDB;

import java.io.BufferedReader;
import java.io.IOException;

import java.nio.charset.MalformedInputException;
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
        try {
            con = ConnectDB.getInstance();
            Statement stmt = null;
            String query = "SELECT DISTINCT TABLE_SCHEMA FROM TABLES";
            try {
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    databases.add(rs.getString("TABLE_SCHEMA"));

                }
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

    public static void showData (Object table)
    {
        Connection con = null;
        String query = "SELECT * FROM "+table;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ResultSetMetaData rsmd = null;
        try
        {
            con = ConnectDB.getInstance();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rsmd = rs.getMetaData();
            while(rs.next()) {
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                        final String columnName = rsmd.getColumnName(i);
                    if(rs.getObject(i)!=null) {
                        System.out.print(rsmd.getColumnName(i) + ": " + rs.getObject(columnName).toString() + " ");
                    }
                }
                System.out.println();
            }
        } catch (SQLException throwables) {
            try {
                System.out.println(rsmd.getColumnType(1));
            } catch (SQLException e) {
                e.printStackTrace();
            }
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

}
