
package Servidor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SensorDAO {
    // Insertar datos
    public void insertar(int x, int y, int z, String fecha, String hora) {
        String sql = "INSERT INTO datos_sensor (x, y, z, fecha_de_captura, hora_de_captura) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionSQLite.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, x);
            stmt.setInt(2, y);
            stmt.setInt(3, z);
            stmt.setString(4, fecha);
            stmt.setString(5, hora);
            stmt.executeUpdate();

            System.out.println("[SQLite] Registro insertado");

        } catch (Exception e) {
            System.out.println("[SQLite] Error insertando: " + e.getMessage());
        }
    }

    // Consultar datos entre fechas
    public String consultar(String fechaInicio, String fechaFin) {
        String sql = "SELECT * FROM datos_sensor WHERE fecha_de_captura BETWEEN ? AND ?";
        StringBuilder json = new StringBuilder("[");

        try (Connection conn = ConexionSQLite.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, fechaInicio);
            stmt.setString(2, fechaFin);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                json.append("{")
                        .append("\"x\":").append(rs.getInt("x")).append(",")
                        .append("\"y\":").append(rs.getInt("y")).append(",")
                        .append("\"z\":").append(rs.getInt("z")).append(",")
                        .append("\"fecha\":\"").append(rs.getString("fecha_de_captura")).append("\",")
                        .append("\"hora\":\"").append(rs.getString("hora_de_captura")).append("\"")
                        .append("},");
            }

            if (json.charAt(json.length() - 1) == ',')
                json.deleteCharAt(json.length() - 1);

        } catch (Exception e) {
            System.out.println("[SQLite] Error consultando: " + e.getMessage());
        }

        json.append("]");
        return json.toString();
    }
}
