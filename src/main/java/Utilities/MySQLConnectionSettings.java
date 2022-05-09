package Utilities;

/**
 * Classe que defineix els paràmetres de la connexió a la BBDD ubicada en el HOST
 */
public final class MySQLConnectionSettings {
    private static final String URL ="jdbc:mysql://localhost:3306/";
    private static String database;
    public static String characterSet;
    private static String usr;
    private static String pwd;

    /**
     * Constructor privat sense paràmetres perquè no volem que es creeïn objectes de la classe
     */
    private MySQLConnectionSettings(){}

    /**
     * Getter per a modificar la BD a la qual ens volem connectar
     * @return
     */
    public static String getDatabase() {
        return database;
    }

    /**
     * Setter per a canviar la BD a la qual ens volem connectar
     * @param database
     */
    public static void setDatabase(String database) {
        MySQLConnectionSettings.database = database;
    }

    /**
     * Getter de usr de BD
     * @return
     */
    public static String getUsr() {
        return usr;
    }

    /**
     * Setter de usr de la BD
     * @param usr
     */
    public static void setUsr(String usr) {
        MySQLConnectionSettings.usr = usr;
    }

    /**
     * Getter del password del user de la BD
     * @return
     */
    public static String getPwd() {
        return pwd;
    }

    /**
     * Setter del password de l'usuari
     * @param pwd
     */
    public static void setPwd(String pwd) {
        MySQLConnectionSettings.pwd = pwd;
    }

    /**
     * Getter de la URL amb la ubicació de la BD
     * @return
     */
    public static String getUrl(){
        return URL;
    }

}
