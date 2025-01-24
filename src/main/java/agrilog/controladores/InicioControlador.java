package agrilog.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
            // Si hay un error (por ejemplo, correo ya registrado), devolvemos el mensaje y código 400 (Bad Request)
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
