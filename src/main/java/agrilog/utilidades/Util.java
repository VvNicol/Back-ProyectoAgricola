package agrilog.utilidades;

import java.time.LocalDateTime;
import java.util.UUID;

import agrilog.modelos.UsuarioModelo;

public class Util {

	public static String generarTokenConCorreo(UsuarioModelo usuario) {
        String token = UUID.randomUUID().toString();
        usuario.setToken(token);
        usuario.setFechaExpiracionToken(LocalDateTime.now().plusMinutes(5));
        return token;
    }
}
