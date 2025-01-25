package agrilog.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import agrilog.modelos.UsuarioModelo;
import agrilog.servicios.UsuarioInterfaz;

@RestController
@RequestMapping("/inicio")
public class InicioControlador {

	@Autowired
	private UsuarioInterfaz ui;

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

	@GetMapping("/verificar")
	public ResponseEntity<String> validarCorreo(@RequestParam("token") String token) {
		try {

			boolean validacionExitosa = ui.validarCorreo(token);

			if (validacionExitosa) {
				System.out.println("Correo verificado con exito");
				return new ResponseEntity<>("Correo verificado con exito", HttpStatus.OK);
				
			} else {
				System.out.println("El token es invalido o ha caducado");
				return new ResponseEntity<>("El token es invalido o ha caducado", HttpStatus.BAD_REQUEST);
				
			}

		} catch (Exception e) {
			return new ResponseEntity<>("Ocurrió un error al validar el correo: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
}
