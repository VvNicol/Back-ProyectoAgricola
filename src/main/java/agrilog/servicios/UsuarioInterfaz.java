package agrilog.servicios;

import agrilog.modelos.UsuarioModelo;

public interface UsuarioInterfaz {
	
	public void solicitarRecuperacion(String correo) throws Exception;

	void registrarUsuario(UsuarioModelo usuario) throws Exception;

	boolean existeCorreo(String correo);
	
	public boolean verificarContrasenia(String contraseniaIngresada, String contraseniaGuardada);

	boolean validarCorreo(String token);

	boolean iniciarSesion(String correo, String contrasenia) throws Exception;

	public void cambiarContrasenia(String correo, int codigo, String nuevaContrasenia) throws Exception;

}
