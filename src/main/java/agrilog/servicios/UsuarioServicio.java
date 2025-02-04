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
	public void cambiarContrasenia(String correo, int codigo, String nuevaContrasenia) throws Exception {

		UsuarioModelo usuario = usuarioRepositorio.findByCorreo(correo);

		if (usuario == null) {
			throw new Exception("Usuario no encontrado.");
		}

		if (Integer.valueOf(usuario.getCodigoRecuperacion()).equals(Integer.valueOf(codigo))
				&& usuario.getCodigoExpiracionFecha().isAfter(LocalDateTime.now())) {
			
			String contraseniaEncriptada = ContraseniaEncriptada.encode(nuevaContrasenia);
			usuario.setContrasenia(contraseniaEncriptada);

			usuario.setCodigoRecuperacion(0);
			usuario.setCodigoExpiracionFecha(null);

			usuarioRepositorio.save(usuario);

		} else {
			throw new Exception("El codigo es incorrecto o ha expirado.");
		}

	}

	@Override
	public void solicitarRecuperacion(String correo) throws Exception {

		UsuarioModelo usuario = usuarioRepositorio.findByCorreo(correo);

		if (usuario != null) {

			int codigo = Util.generarCodigo();
			usuario.setCodigoRecuperacion(codigo);
			usuario.setCodigoExpiracionFecha(LocalDateTime.now().plusMinutes(10));
			correoServicio.correoRecuperacionConCodigo(usuario.getCorreo(), codigo);
			usuarioRepositorio.save(usuario);

		} else {
			throw new Exception("Usuario no encontrado");
		}
	}

	@Override
	public void registrarUsuario(UsuarioModelo usuario) throws Exception {

		if (usuarioRepositorio.existsByCorreo(usuario.getCorreo())) {
			throw new Exception("El correo ya está registrado.");
		}

		String asignacionRol = "usuario";
		usuario.setRol(asignacionRol);

		String encriptarContrasenia = this.ContraseniaEncriptada.encode(usuario.getContrasenia());
		usuario.setContrasenia(encriptarContrasenia);
		usuario.setFechaRegistro(java.time.LocalDateTime.now());

		String token = Util.generarTokenConCorreo(usuario);
		usuarioRepositorio.save(usuario);

		correoServicio.correoDeVerificacion(usuario.getCorreo(), token);
	}

	@Override
	public boolean validarCorreo(String token) {
		UsuarioModelo usuario = usuarioRepositorio.findByToken(token);

		// Si el usuario no existe o el token no es válido
		if (usuario == null) {
			return false;
		}

		// Verifica si el token sigue siendo válido
		if (usuario.getTokenExpiracionFecha().isAfter(LocalDateTime.now())) {
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

		if (usuario == null) {
			throw new Exception("El correo no esta registrado");
		}

		if (!usuario.isCorreoValidado()) {

			String token = Util.generarTokenConCorreo(usuario);
			usuarioRepositorio.save(usuario);
			correoServicio.correoDeVerificacion(usuario.getCorreo(), token);

			throw new Exception("El correo no ha sido validado. Revisa tu bandeja de entrada.");

		}

		if (!ContraseniaEncriptada.matches(contrasenia, usuario.getContrasenia())) {
			throw new ELException("Contraseña incorrecta.");
		}

		return true;
	}

}
