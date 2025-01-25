package agrilog.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class CorreoServicio {
	
	@Autowired
	private JavaMailSender correoRemitente;
		
	
	public void enviarCorreo(String para, String asunto, String contenidoHtml) throws MessagingException {
		
		MimeMessage mensaje = correoRemitente.createMimeMessage();
		MimeMessageHelper asistente = new MimeMessageHelper(mensaje, true);
		
		asistente.setTo(para);
		asistente.setSubject(asunto);
		asistente.setText(contenidoHtml, true);
		
		correoRemitente.send(mensaje);
	}
	
	

}
