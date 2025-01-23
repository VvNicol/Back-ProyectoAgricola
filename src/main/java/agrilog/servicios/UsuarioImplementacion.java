package agrilog.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agrilog.modelos.UsuarioModelo;
import agrilog.repositorios.UsuarioRepositorio;

@Service
public class UsuarioImplementacion implements UsuarioInterfaz {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Override
	public void registrarUsuario(UsuarioModelo usuario) throws Exception {

		if (usuarioRepositorio.existsByCorreo(usuario.getCorreo())) {
			throw new Exception("El correo ya está registrado.");
		}
		usuario.setFechaRegistro(java.time.LocalDateTime.now());
		usuarioRepositorio.save(usuario);
	}

	@Override
	public boolean existeCorreo(String correo) {
		return usuarioRepositorio.existsByCorreo(correo);
	}

}
