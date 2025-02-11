package agrilog.excepciones;

public class TokenNoProporcionadoExcepcion extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TokenNoProporcionadoExcepcion(String mensaje) {
		super(mensaje);
	}

}
