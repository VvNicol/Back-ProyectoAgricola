package agrilog.servicios;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import agrilog.modelos.UsuarioModelo;
import agrilog.repositorios.UsuarioRepositorio;
import jakarta.mail.MessagingException;

@Service
public class UsuarioImplementacion implements UsuarioInterfaz {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Autowired
	private PasswordEncoder ContraseniaEncriptada;

	@Autowired
	private CorreoServicio emailService;

	public void enviarCorreoDeVerificacion(String correo, String token) {
	    String asunto = "Verificación de correo :p";
	    String contenido = "<h1>Verifica tu correo electrónico</h1>" +
	            "<p>Haz clic en el siguiente botón para verificar tu cuenta:</p>" +
	            "<a href=\"http://localhost:8080/inicio/verificar?token=" + token + "\" " +
	            "style=\"padding: 10px 20px; color: white; background-color: #007bff; text-decoration: none; border-radius: 5px;\">Verificar correo</a>" +
	            "<p>Si no reconoces esta acción, ignora este mensaje.</p>";

	    try {
	        emailService.enviarCorreo(correo, asunto, contenido);
	    } catch (MessagingException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void registrarUsuario(UsuarioModelo usuario) throws Exception {
	    if (usuarioRepositorio.existsByCorreo(usuario.getCorreo())) {
	        throw new Exception("El correo ya está registrado.");
	    }

	    String encriptarContrasenia = this.ContraseniaEncriptada.encode(usuario.getContrasenia());
	    usuario.setContrasenia(encriptarContrasenia);
	    usuario.setFechaRegistro(java.time.LocalDateTime.now());

	    // Generar token único y fecha de expiración
	    String token = UUID.randomUUID().toString();
	    usuario.setToken(token);
	    usuario.setFechaExpiracionToken(LocalDateTime.now().plusMinutes(5)); // Token expira en 5 minutos

	    usuarioRepositorio.save(usuario);

	    // Enviar correo de verificación
	    enviarCorreoDeVerificacion(usuario.getCorreo(), token);
	}
	
	@Override
	public boolean validarCorreo(String token) {
	    UsuarioModelo usuario = usuarioRepositorio.findByToken(token);

	    // Si el usuario no existe o el token no es válido
	    if (usuario == null) {
	        return false;
	    }

	    // Verifica si el token sigue siendo válido
	    if (usuario.getFechaExpiracionToken().isAfter(LocalDateTime.now())) { // Si el token aún no ha caducado
	        usuario.setCorreoValidado(true); // Marca el correo como validado
	        usuario.setToken(null); // Limpia el token después de la validación
	        usuarioRepositorio.save(usuario); // Guarda los cambios en la base de datos
	        return true;
	    }

	    // Si el token ya ha caducado
	    return false;
	}

	@Override
	public boolean existeCorreo(String correo) {
		return usuarioRepositorio.existsByCorreo(correo);
	}

	@Override
	public boolean verificarContrasenia(String contraseniaIngresada, String contraseniaGuardada) {
		return ContraseniaEncriptada.matches(contraseniaIngresada, contraseniaGuardada);
	}

	

}
