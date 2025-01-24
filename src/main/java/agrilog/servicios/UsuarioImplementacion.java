package agrilog.servicios;

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

    public void enviarCorreoDeVerificacion(String correo) {
        String asunto = "Verificación de correo :p";
        String contenido = "<h1>Bienvenido</h1><p>Hola bebito, mi primer correo enviado desde java </p>";
        
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
		usuarioRepositorio.save(usuario);
		
		enviarCorreoDeVerificacion(usuario.getCorreo());
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
