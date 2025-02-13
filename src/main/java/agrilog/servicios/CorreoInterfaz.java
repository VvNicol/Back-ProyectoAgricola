package agrilog.servicios;

import jakarta.mail.MessagingException;

public interface CorreoInterfaz {

	public void correoRecuperacionConCodigo(String correo, int codigo);

	public void correoDeVerificacion(String correo, String token);

	public void enviarCorreo(String para, String asunto, String contenidoHtml) throws MessagingException;

}
