package agrilog.excepciones;

public class TokenInvalidoExcepcion extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TokenInvalidoExcepcion(String mensaje) {
		super(mensaje);
	}

}
