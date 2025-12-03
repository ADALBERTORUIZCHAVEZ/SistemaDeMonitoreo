
package Servidor;

import java.sql.Connection;
import java.sql.Statement;

public class BaseDeDatos {
    public static void inicializar() {
        String sqlTabla = "CREATE TABLE IF NOT EXISTS datos_sensor ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + "x INTEGER NOT NULL, "
                + "y INTEGER NOT NULL, "
                + "z INTEGER NOT NULL, "
                + "fecha_de_captura TEXT NOT NULL, "
                + "hora_de_captura TEXT NOT NULL"
                + ");";

        try (Connection conn = ConexionSQLite.conectar();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sqlTabla);
            System.out.println("[SQLite] Base de datos lista.");

        } catch (Exception e) {
            System.out.println("[SQLite] Error creando tabla: " + e.getMessage());
        }
    }
    
    
}
