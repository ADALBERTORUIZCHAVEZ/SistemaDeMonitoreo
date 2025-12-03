
package Cliente;

public class ClienteMain {
    public static void main(String[] args) {

        ClienteSocket cliente = new ClienteSocket();

        if(cliente.conectar("localhost", 5000)) {

            // Enviamos un registro para insertar
            String json = MensajeCliente.crearMensajeInsertar(10, 20, 30, "2025-11-28", "22:45:00");
            String respuesta = cliente.enviarMensaje(json);

            System.out.println("Respuesta del servidor: " + respuesta);

            cliente.cerrar();
        }
    }
}
