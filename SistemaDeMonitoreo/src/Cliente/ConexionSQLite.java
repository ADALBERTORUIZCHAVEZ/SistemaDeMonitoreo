
package Cliente;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionSQLite {
    private static Connection conn = null;

    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                Class.forName("org.sqlite.JDBC");
                conn = DriverManager.getConnection("jdbc:sqlite:C:/Users/DELL/Documents/NetBeansProjects/SistemaDeMonitoreo/monitorBD.db");
            }
        } catch (Exception e) {
            System.out.println("[SQLite Cliente] Error conexion: " + e.getMessage());
        }
        return conn;
    }
}
