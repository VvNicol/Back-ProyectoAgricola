package agrilog.servicios;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import agrilog.modelos.UsuarioModelo;
import agrilog.repositorios.UsuarioRepositorio;
import agrilog.utilidades.Util;
import jakarta.el.ELException;

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

		String token = Util.generarTokenConCorreo(usuario);
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

	@Override
	public boolean iniciarSesion(String correo, String contrasenia) throws Exception {

		UsuarioModelo usuario = usuarioRepositorio.findByCorreo(correo);
		/*String mensaje = "";*/
		
		
		if (usuario == null) {
			throw new Exception("El correo no esta registrado");
		}

		if (!usuario.isCorreoValidado()) {

			String token = Util.generarTokenConCorreo(usuario);
			usuarioRepositorio.save(usuario);
			correoServicio.enviarCorreoDeVerificacion(usuario.getCorreo(), token);
			
			
			throw new Exception("El correo no ha sido validado. Revisa tu bandeja de entrada.");

		}

		if (!ContraseniaEncriptada.matches(contrasenia, usuario.getContrasenia())) {
			throw new ELException("Contraseña incorrecta.");
		}

		return true;
	}

}
