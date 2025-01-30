package agrilog.servicios;

import agrilog.modelos.UsuarioModelo;

public interface UsuarioInterfaz {

	void registrarUsuario(UsuarioModelo usuario) throws Exception;

	boolean existeCorreo(String correo);
	
	public boolean verificarContrasenia(String contraseniaIngresada, String contraseniaGuardada);

	boolean validarCorreo(String token);

	boolean iniciarSesion(String correo, String contrasenia) throws Exception;

}
