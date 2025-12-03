
package Cliente;

import java.util.Base64;

public class EncriptadorCliente {
    public static String encriptar(String texto) {
        return Base64.getEncoder().encodeToString(texto.getBytes());
    }

    public static String desencriptar(String encriptado) {
        return new String(Base64.getDecoder().decode(encriptado));
    }
}
