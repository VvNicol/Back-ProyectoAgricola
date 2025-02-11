package agrilog.controladores;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import agrilog.modelos.CultivoModelo;
import agrilog.seguridad.JwtInterceptor;
import agrilog.servicios.CultivoServicio;
import agrilog.servicios.NotificacionServicio;

@RestController
@RequestMapping("/usuario")
public class UsuarioControlador {

	@Autowired
	private CultivoServicio cultivoServicio;

	@Autowired
	private NotificacionServicio notificacionServicio;

	@PreAuthorize("hasRole('usuario')")
	@PostMapping("/cultivo-registrar")
	public ResponseEntity<String> registrarCultivo(@RequestBody CultivoModelo cultivo) {
		try {

			String correoUsuario = JwtInterceptor.obtenerCorreoUsuario();
			cultivoServicio.registrarCultivo(cultivo, correoUsuario);

			return new ResponseEntity<>("Cultivo registrado exitosamente", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>("Error al registrar el cultivo: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('usuario')")
	@PostMapping("/notificacion")
	public ResponseEntity<Map<String, String>> notificaciones(@RequestBody Map<String, Object> data) {
		try {
			String correoUsuario = JwtInterceptor.obtenerCorreoUsuario();

			notificacionServicio.mensajeBienvenida(correoUsuario, null);

			return new ResponseEntity<>(Map.of("mensaje", "Notificación enviada con éxito"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(Map.of("error", "Error al enviar la notificación: " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

}
