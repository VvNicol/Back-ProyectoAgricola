package agrilog.servicios;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agrilog.modelos.NotificacionModelo;
import agrilog.repositorios.NotificacionRepositorio;

@Service
public class NotificacionServicio implements NotificacionInterfaz {
	
	@Autowired
	private NotificacionRepositorio notificacionRepositorio;

	public void mensajeBienvenida(String correoUsuario, Long cultivoId) throws Exception {
		
        String mensaje = "Bienvenido al sistema de cultivos";
        LocalDate fechaMensaje = LocalDate.now();

        NotificacionModelo notificacion = new NotificacionModelo(cultivoId, mensaje, 1, fechaMensaje);

        notificacionRepositorio.save(notificacion);
    }

}
