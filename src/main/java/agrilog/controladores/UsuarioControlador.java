package agrilog.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	@PostMapping("/cultivo-modificar")
	public ResponseEntity<Map<String, String>> cultivoModificar(@RequestBody CultivoModelo cultivo) {

		Map<String, String> responde = new HashMap<>();

		try {

			String correoUsuario = JwtInterceptor.obtenerCorreoUsuario();
			cultivoServicio.cultivoModificar(cultivo, correoUsuario);

			responde.put("mensaje", "Se ha modificado con exito");
			return new ResponseEntity<>(responde, HttpStatus.BAD_REQUEST);

		} catch (Exception e) {

			responde.put("error", e.getMessage());
			return new ResponseEntity<>(responde, HttpStatus.BAD_REQUEST);

		}
	}

	@PreAuthorize("hasRole('usuario')")
	@DeleteMapping("/cultivo-eliminar")
	public ResponseEntity<Map<String, String>> cultivoEliminar(@RequestBody Map<String, Long> datos) {
		Map<String, String> respuesta = new HashMap<>();

		try {
			String correoUsuario = JwtInterceptor.obtenerCorreoUsuario();
			Long cultivoId = datos.get("cultivoId");

			cultivoServicio.cultivoEliminar(cultivoId, correoUsuario);

			respuesta.put("mensaje", "Cultivo eliminado con éxito");
			return new ResponseEntity<>(respuesta, HttpStatus.OK);

		} catch (Exception e) {
			respuesta.put("error", e.getMessage());
			return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasRole('usuario')")
	@PostMapping("/cultivos-ver")
	public ResponseEntity<Map<String, List<CultivoModelo>>> listarCultivosPorParcela() {
	    try {
	        String correoUsuario = JwtInterceptor.obtenerCorreoUsuario();
	        List<CultivoModelo> cultivos = cultivoServicio.obtenerCultivosPorUsuario(correoUsuario);
	        
	        Map<String, List<CultivoModelo>> respuesta = new HashMap<>();
	        respuesta.put("cultivos", cultivos);
	        
	        return ResponseEntity.ok(respuesta);
	    } catch (Exception e) {
	        Map<String, List<CultivoModelo>> errorMap = new HashMap<>();
	        errorMap.put("cultivos error", List.of());
	        return ResponseEntity.badRequest().body(errorMap);
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
