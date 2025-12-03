
package Servidor;

public class Mensaje {
    public String tipo = "";
    public int x, y, z;
    public String fecha, hora;
    public String fechaInicio, fechaFin;

    // Convierte JSON de tipo String a un objeto Mensaje
    public static Mensaje desdeJSON(String json) {
        Mensaje msg = new Mensaje();

        json = json.replace("{", "").replace("}", "");
        String[] partes = json.split(",");

        for (String p : partes) {
            String[] kv = p.split(":", 2);

            if (kv.length < 2) continue;

            String key = kv[0].replace("\"", "").trim();
            String val = kv[1].replace("\"", "").trim();

            switch (key) {
                case "tipo": msg.tipo = val; break;
                case "x": msg.x = Integer.parseInt(val); break;
                case "y": msg.y = Integer.parseInt(val); break;
                case "z": msg.z = Integer.parseInt(val); break;

                case "fecha": msg.fecha = val; break;
                case "hora": msg.hora = val; break;

                case "fechaInicio": msg.fechaInicio = val; break;
                case "fechaFin": msg.fechaFin = val; break;
            }
        }

        return msg;
    }
}
