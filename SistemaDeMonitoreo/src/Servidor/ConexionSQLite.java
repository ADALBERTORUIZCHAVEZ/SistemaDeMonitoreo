
package Servidor;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionSQLite {
    private static final String URL = "jdbc:sqlite:monitorBD.db";

    public static Connection conectar() {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(URL);
        } catch (Exception e) {
            System.out.println("[SQLite] Error de conexion: " + e.getMessage());
            return null;
        }
    }
}
