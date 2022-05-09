package Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe que implementa el patró Singleton per a establir la connexió amb la BD
 */
public final class ConnectionDB {
    /**
     * Es declara una variable del tipus java.sql.Connection; per convenció es denomina "instance".
     * Aquesta objecte és el que es crearà un sol cop seguint el patró Singleton
     */
    private static Connection instance;

    /**
     * Constructor privat per tal de que no es puguin crear objectes de la classe ConnectionDB
     */
    private ConnectionDB(){}

    /**
     * Per a usar una única instància de la classe, s'haurà de cridar al mètode estàtic getInstance().
     * La primera vegada que es cridi serà null, els posteriors cops retornarà l'objecte Connection ja inicialitzat.
     * Això assegura que només es creeï un objecte del tipus java.sql.Connection.
     *
     * @return Connection
     * @throws SQLException
     */
    public static Connection getInstance() throws SQLException {
        if(instance == null) {
//            String database = Utilities.llegirParaula("Escriu la BD a la qual et vols connectar");
            String database = "sakila";
//            String usr = Utilities.llegirParaula("Escriu l'usuari amb el que et vols connectar a BD");
            String usr = "drusito";
//            String pwd = Utilities.llegirParaula("Escriu el password de l'usuari");
            String pwd = "Degradao";
            MySQLConnectionSettings.setDatabase(database);
            MySQLConnectionSettings.setUsr(usr);
            MySQLConnectionSettings.setPwd(pwd);

            String url = MySQLConnectionSettings.getUrl() + database;

            try{
                Class.forName("com.mysql.jdbc.Driver");
            }catch (ClassNotFoundException e){
                e.printStackTrace();
            }

            // Usem el mètode getConnection(url, user, passsword) de la classe jdbc.jar.DriverManager
            instance = DriverManager.getConnection(url, usr, pwd);

            System.out.println(instance.toString());
            System.out.println("Connexió establerta amb la BD " + MySQLConnectionSettings.getDatabase());
        }
            return instance;
    }

    /**
     * Mètode per a tancat la connexió a BD
     *
     * @throws SQLException
     */
    public static void closeConnection() throws SQLException {
        if(instance != null){
            instance.close();
            System.out.println("Tanquem la connexió a la BD " + MySQLConnectionSettings.getDatabase() + " per l'usuari " + MySQLConnectionSettings.getUsr());
        }
    }
}
