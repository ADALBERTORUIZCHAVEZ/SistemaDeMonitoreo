
package Servidor;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) {
        int puerto = 5000;
        BaseDeDatos.inicializar();
        
        System.out.println("=== SERVIDOR INICIADO ===");
        System.out.println("Esperando clientes en el puerto " + puerto + "...");

        try (ServerSocket serverSocket = new ServerSocket(puerto)) {

            while (true) {
                Socket cliente = serverSocket.accept();
                System.out.println("[Servidor] Nuevo cliente conectado: " + cliente.getInetAddress());

                ClienteHandler handler = new ClienteHandler(cliente);
                handler.start();
            }

        } catch (Exception e) {
            System.out.println("[Servidor] Error: " + e.getMessage());
        }
    }
}
