package agrilog.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import agrilog.servicios.CultivoServicio;

@RestController
@RequestMapping("/usuario")
public class UsuarioControlador {
	
	@Autowired
	private CultivoServicio cs;
	
	@PreAuthorize("hasRole('usuario')")
	@GetMapping("/cultivo-registrar")
	public String accesoUsuario() {
		return "Acceso permitido a Usuario";
	}
	
}
