package Utilities;

/**
 * Classe que defineix els paràmetres de la connexió a la BBDD ubicada en el HOST
 */
public final class MySQLConnectionSettings {
    public static final String URL = "jdbc:mysql://localhost:3306/";
    public static String database = "mysql";
    public static final String CHARACTER_SET = "latin1";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";

    private MySQLConnectionSettings(){};

    public static void setDatabase(String database){
        MySQLConnectionSettings.database = database;
    }
}
