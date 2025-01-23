package agrilog.servicios;

import org.springframework.stereotype.Service;

import agrilog.modelos.UsuarioModelo;

@Service
public interface UsuarioInterfaz {

	void registrarUsuario(UsuarioModelo usuario) throws Exception;

	boolean existeCorreo(String correo);

	

}
