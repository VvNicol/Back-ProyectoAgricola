package agrilog.controladores;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import agrilog.modelos.UsuarioModelo;
import agrilog.servicios.UsuarioInterfaz;
import agrilog.utilidades.JwtUtil;

@CrossOrigin
@RestController
@RequestMapping("/inicio")
public class InicioControlador {

	@Autowired
	private UsuarioInterfaz ui;

	@GetMapping("/verificar")
	public ResponseEntity<String> validarCorreo(@RequestParam("token") String token) {
		try {

			boolean validacionExitosa = ui.validarCorreo(token);

			if (validacionExitosa) {
				return new ResponseEntity<>("Correo verificado con exito", HttpStatus.OK);

			} else {
				return new ResponseEntity<>("El token es invalido o ha caducado", HttpStatus.BAD_REQUEST);

			}

		} catch (Exception e) {
			return new ResponseEntity<>("Ocurrió un error al validar el correo: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@PostMapping("/registro")
	public ResponseEntity<String> registrarUsuario(@RequestBody UsuarioModelo usuario) {
		try {
			ui.registrarUsuario(usuario);

			return new ResponseEntity<>("Usuario registrado con éxito.", HttpStatus.CREATED);
		} catch (Exception e) {
			// Si hay un error (por ejemplo, correo ya registrado), devolvemos el mensaje y
			// código 400 (Bad Request)
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/iniciar-sesion")
	public ResponseEntity<Map<String, Object>> iniciarSesion(@RequestBody UsuarioModelo usuario) {
		Map<String, Object> response = new HashMap<>();

		try {

			boolean sesionIniciada = ui.iniciarSesion(usuario.getCorreo(), usuario.getContrasenia());

			if (sesionIniciada) {

				String rol = usuario.getRol();
				String token = JwtUtil.generarToken(usuario.getCorreo(), rol);

				// Definir URL de redirección
				String urlRedireccion = rol.equals("usuario") ? "http://localhost:4200/inicio/usuario?token=" + token
						: "http://localhost:4200/inicio/admin?token=" + token;

				// respuesta JSON
				response.put("mensaje", "Inicio de sesión exitoso.");
				response.put("token", token);
				response.put("url", urlRedireccion);
				response.put("rol", rol);

				return new ResponseEntity<>(response, HttpStatus.OK);
			}

			response.put("mensaje", "No se pudo iniciar sesión.");
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);

		} catch (Exception e) {
			// Captura el mensaje de la excepción y lo devuelve en JSON
			response.put("mensaje", "Error al iniciar sesión.");
			response.put("error", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		}
	}

}
