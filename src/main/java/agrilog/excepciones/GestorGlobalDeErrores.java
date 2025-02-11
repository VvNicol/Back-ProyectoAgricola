package agrilog.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GestorGlobalDeErrores {

	@ExceptionHandler(TokenNoProporcionadoExcepcion.class)
	public ResponseEntity<String> gestorTokenNoProporcionadoExcepcion(TokenNoProporcionadoExcepcion ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(TokenInvalidoExcepcion.class)
	public ResponseEntity<String> gestorTokenInvalidoException(TokenInvalidoExcepcion ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
	}
}
