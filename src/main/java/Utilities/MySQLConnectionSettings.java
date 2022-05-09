package com.company.utils;

/**
 * Classe que defineix els paràmetres de la connexió a la BBDD ubicada en el HOST
 */
public final class MySQLConnectionSettings {
    public static final String URL = "jdbc:mysql://localhost:3306/";
    public static String database = "test";
    public static final String CHARACTER_SET = "latin1";
    public static final String USERNAME = "intellij_RW";
    public static final String PASSWORD = "1234";

    private MySQLConnectionSettings(){};

    public void setDatabase(String database){
        MySQLConnectionSettings.database = database;
    }
}
