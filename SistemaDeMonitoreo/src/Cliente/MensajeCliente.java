
package Cliente;

public class MensajeCliente {
    public static String crearMensajeInsertar(int x, int y, int z, String fecha, String hora) {
        return "{"
                + "\"tipo\":\"insertar\","
                + "\"x\":" + x + ","
                + "\"y\":" + y + ","
                + "\"z\":" + z + ","
                + "\"fecha\":\"" + fecha + "\","
                + "\"hora\":\"" + hora + "\""
                + "}";
    }

    public static String crearMensajeConsulta(String fechaInicio, String fechaFin) {
        return "{"
                + "\"tipo\":\"consultar\","
                + "\"fechaInicio\":\"" + fechaInicio + "\","
                + "\"fechaFin\":\"" + fechaFin + "\""
                + "}";
    }
    public static String crearMensajeListar() {
    return "{\"tipo\":\"listar\"}";
}
}
