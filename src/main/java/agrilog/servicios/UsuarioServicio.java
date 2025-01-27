package agrilog.servicios;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import agrilog.modelos.UsuarioModelo;
import agrilog.repositorios.UsuarioRepositorio;

@Service
public class UsuarioServicio implements UsuarioInterfaz {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Autowired
	private PasswordEncoder ContraseniaEncriptada;

	@Autowired
	private CorreoServicio correoServicio;

	@Override
	public void registrarUsuario(UsuarioModelo usuario) throws Exception {
	    if (usuarioRepositorio.existsByCorreo(usuario.getCorreo())) {
	        throw new Exception("El correo ya está registrado.");
	    }

	    String encriptarContrasenia = this.ContraseniaEncriptada.encode(usuario.getContrasenia());
	    usuario.setContrasenia(encriptarContrasenia);
	    usuario.setFechaRegistro(java.time.LocalDateTime.now());

	    String token = UUID.randomUUID().toString();
	    usuario.setToken(token);
	    usuario.setFechaExpiracionToken(LocalDateTime.now().plusMinutes(5)); // Token expira en 5 minutos

	    usuarioRepositorio.save(usuario);

	    correoServicio.enviarCorreoDeVerificacion(usuario.getCorreo(), token);
	}
	
	@Override
	public boolean validarCorreo(String token) {
	    UsuarioModelo usuario = usuarioRepositorio.findByToken(token);

	    // Si el usuario no existe o el token no es válido
	    if (usuario == null) {
	        return false;
	    }

	    // Verifica si el token sigue siendo válido
	    if (usuario.getFechaExpiracionToken().isAfter(LocalDateTime.now())) {
	        usuario.setCorreoValidado(true);
	        usuario.setToken(null);
	        usuarioRepositorio.save(usuario);
	        return true;
	    }

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
