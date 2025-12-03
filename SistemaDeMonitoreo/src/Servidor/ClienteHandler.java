
package Servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteHandler extends Thread{
    private Socket socket;

    public ClienteHandler(Socket socket) {
        this.socket = socket;
    }
    
    @Override 
    public void run() {

        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

            SensorDAO dao = new SensorDAO();

            while (true) {
                String mensajeEncriptado = entrada.readLine();

                if (mensajeEncriptado == null) break;

                String mensaje = Encriptador.desencriptar(mensajeEncriptado);
                System.out.println("[Servidor] Mensaje recibido: " + mensaje);

                Mensaje msg = Mensaje.desdeJSON(mensaje);

                if (msg.tipo.equals("insertar")) {
                    dao.insertar(msg.x, msg.y, msg.z, msg.fecha, msg.hora);
                    salida.println(Encriptador.encriptar("OK_INSERTADO"));
                }
                else if (msg.tipo.equals("consultar")) {
                    String datos = dao.consultar(msg.fechaInicio, msg.fechaFin);
                    salida.println(Encriptador.encriptar(datos));
                }
            }

            socket.close();

        } catch (Exception e) {
            System.out.println("[Handler] Error: " + e.getMessage());
        }
    }
}
