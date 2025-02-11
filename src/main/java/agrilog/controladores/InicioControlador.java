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
import agrilog.repositorios.UsuarioRepositorio;
import agrilog.servicios.UsuarioInterfaz;
import agrilog.utilidades.JwtUtil;

@CrossOrigin
@RestController
@RequestMapping("/inicio")
public class InicioControlador {

	@Autowired
	private UsuarioInterfaz ui;

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

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
	
	@PostMapping("/nueva-contrasenia")
	public ResponseEntity<Map<String, String>> cambiarConstrasenia(@RequestBody Map<String, String> respuestaBody) {
		
		Map<String, String> responde = new HashMap<>();
		
		try {
			String correo = respuestaBody.get("correo");
		
			String nuevaContrasenia = respuestaBody.get("nuevaContrasenia");
			
			ui.cambiarContrasenia(correo,nuevaContrasenia);
			
			responde.put("mensaje", "Contraseña cambiada con éxito.");
			return new ResponseEntity<>(responde, HttpStatus.OK);
			
		} catch (Exception e) {
			
			responde.put("mensaje", "Error al cambiar la contraseña.");
			responde.put("error", e.getMessage());
			return new ResponseEntity<>(responde, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping("/verificar-codigo")
	public ResponseEntity<Map<String, String>> verificarCodigo(@RequestBody Map<String, String> respuestaBody) {
		
		Map<String, String> responde = new HashMap<>();

		try {
			
			String correo = respuestaBody.get("correo");
			int codigo = Integer.parseInt(respuestaBody.get("codigo"));
			ui.verificarCodigo(correo, codigo);

			responde.put("mensaje", "Se ha verificado con exito el codigo.");

			return new ResponseEntity<>(responde, HttpStatus.OK);

		} catch (Exception e) {
			responde.put("mensaje", "Error al intentar verificar el codigo.");
			responde.put("error", e.getMessage());
			return new ResponseEntity<>(responde, HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("/enviar-codigo")
	public ResponseEntity<Map<String, String>> recuperarContrasenia(@RequestBody Map<String, String> respuestaBody) {
		Map<String, String> responde = new HashMap<>();

		try {
			
			String correo = respuestaBody.get("correo");
			ui.enviarCodigoAlCorreo(correo);

			responde.put("mensaje", "Se ha enviado un codigo al correo.");

			return new ResponseEntity<>(responde, HttpStatus.OK);

		} catch (Exception e) {
			responde.put("mensaje", "Error al intentar enviar el correo.");
			responde.put("error", e.getMessage());
			return new ResponseEntity<>(responde, HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("/registro")
	public ResponseEntity<String> registrarUsuario(@RequestBody UsuarioModelo usuario) {
		try {
			ui.registrarUsuario(usuario);

			return new ResponseEntity<>("Usuario registrado con éxito.", HttpStatus.CREATED);
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/iniciar-sesion")
	public ResponseEntity<Map<String, Object>> iniciarSesion(@RequestBody UsuarioModelo usuario) {
		Map<String, Object> responde = new HashMap<>();

		try {

			boolean sesionIniciada = ui.iniciarSesion(usuario.getCorreo(), usuario.getContrasenia());

			if (sesionIniciada) {

				UsuarioModelo usuarioBD = usuarioRepositorio.findByCorreo(usuario.getCorreo());

				String rol = usuarioBD.getRol();
				String token = JwtUtil.generarToken(usuario.getCorreo(), rol);

				System.out.print(usuarioBD);

				String urlRedireccion = "http://localhost:4200/inicio/" + rol + "?token=" + token;

				responde.put("mensaje", "Inicio de sesión exitoso.");
				responde.put("token", token);
				responde.put("url", urlRedireccion);
				responde.put("rol", rol);
				System.out.print("rol" + rol);

				return new ResponseEntity<>(responde, HttpStatus.OK);
			}

			responde.put("mensaje", "No se pudo iniciar sesión.");
			return new ResponseEntity<>(responde, HttpStatus.UNAUTHORIZED);

		} catch (Exception e) {
			// Captura el mensaje de la excepción y lo devuelve en JSON
			responde.put("mensaje", "Error al iniciar sesión.");
			responde.put("error", e.getMessage());
			return new ResponseEntity<>(responde, HttpStatus.UNAUTHORIZED);
		}
	}

}
