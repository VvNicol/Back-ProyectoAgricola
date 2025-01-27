package agrilog.servicios;

import jakarta.mail.MessagingException;

public interface CorreoInterfaz {
	
	public void enviarCorreoDeVerificacion(String correo, String token);
	public void enviarCorreo(String para, String asunto, String contenidoHtml) throws MessagingException;
}
