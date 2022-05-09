package Utilities;

import com.company.utils.ConnectDB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class Actions {
    private Actions() {
    }

    public static void main(String[] args) {
        // Comprovem si el driver del connector de jdbc està importat dins del projecte (jdbc.jar)
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        boolean timeToQuit = false;
        /*
         * try-catch with resources: BufferedReader i Connection
         */
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
             Connection con = ConnectDB.getInstance()) {

            createTable(con);

            do {
                timeToQuit = executeMenu(con, in);
            } while (!timeToQuit);

        } catch (IOException e) {
            System.out.println("Error " + e.getClass().getName() + " , quiting.");
            System.out.println("Message: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error closing resource " + e.getClass().getName());
            System.out.println("Message: " + e.getMessage());
        } finally {
            try {
                ConnectDB.closeConnection();
            } catch (SQLException e) {
                System.out.println("Error closing resource " + e.getClass().getName());
            }
        }
    }

    /**
     * Mètode d'exemple per a crear una taula a la BD des de IntelliJ
     *
     * @param con
     * @throws Exception
     */
    private static void createTable(Connection con) throws Exception {
        String query = "CREATE TABLE IF NOT EXISTS `PRODUCTS` (\n" +
                "  `cod` int(5) NOT NULL,\n" +
                "  `name` text COLLATE latin1_spanish_ci NOT NULL\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;";
        try (Statement stmt = con.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            throw new Exception("Error on table PRODUCT creation:" + ex.getMessage(), ex);
        }
    }

    /**
     * Mètode per a mostrar el menú de l'aplicació que permet fer diverses accions a l'usuari de la app
     *
     * @param con
     * @param in
     * @return boolean
     * @throws IOException
     * @throws Exception
     */
    public static boolean executeMenu(Connection con, BufferedReader in) throws IOException, Exception {
        String action;
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

        }

        return false;
    }
}
