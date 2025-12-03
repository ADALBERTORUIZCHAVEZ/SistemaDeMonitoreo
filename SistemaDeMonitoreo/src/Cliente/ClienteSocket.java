
    package Cliente;

    import java.io.BufferedReader;
    import java.io.InputStreamReader;
    import java.io.PrintWriter;
    import java.net.Socket;

    public class ClienteSocket {
        private Socket socket;
        private PrintWriter salida;
        private BufferedReader entrada;

        public ClienteSocket() {

        }

        public boolean conectar(String host, int puerto) {
            try {
                socket = new Socket(host, puerto);
                salida = new PrintWriter(socket.getOutputStream(), true);
                entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                System.out.println("[Cliente] Conectado al servidor.");
                return true;

            } catch (Exception e) {
                System.out.println("[Cliente] Error conectando: " + e.getMessage());
                return false;
            }
        }

        // ============================================================
        //  MÉTODO 1: utilizado por VistaMonitor (NO SE BORRA)
        // ============================================================
        public String enviarMensaje(String mensaje) {
            try {
                salida.println(EncriptadorCliente.encriptar(mensaje));
                return EncriptadorCliente.desencriptar(entrada.readLine());
            } catch (Exception e) {
                return "ERROR";
            }
        }

        // ============================================================
        //  MÉTODO 2: para VistaHistorico
        // ============================================================
        public String consultarHistorico(String fechaInicio, String fechaFin) {
            try {
        String mensaje = "{"
                + "\"tipo\":\"consultar\","
                + "\"inicio\":\"" + fechaInicio + "\","
                + "\"fin\":\"" + fechaFin + "\""
                + "}";

        salida.println(EncriptadorCliente.encriptar(mensaje));

        String respuesta = entrada.readLine();
        return EncriptadorCliente.desencriptar(respuesta);

    } catch (Exception e) {
        System.out.println("[Cliente] Error consultando: " + e.getMessage());
        return "[]";
    }
        }

        public void cerrar() {
            try {
                socket.close();
            } catch (Exception e) {}
        }
    }
