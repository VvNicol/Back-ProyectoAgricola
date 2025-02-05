package agrilog.servicios;

import agrilog.modelos.UsuarioModelo;

public interface UsuarioInterfaz {
	
	public void enviarCodigoAlCorreo(String correo) throws Exception;

	void registrarUsuario(UsuarioModelo usuario) throws Exception;

	boolean existeCorreo(String correo);
	
	public boolean verificarContrasenia(String contraseniaIngresada, String contraseniaGuardada);

	boolean validarCorreo(String token);

	boolean iniciarSesion(String correo, String contrasenia) throws Exception;

	public void cambiarContrasenia(String correo, String nuevaContrasenia) throws Exception;

	public void verificarCodigo(String correo, int codigo) throws Exception;

}
