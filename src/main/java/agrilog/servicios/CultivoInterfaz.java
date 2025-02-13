package agrilog.servicios;

import agrilog.modelos.CultivoModelo;

public interface CultivoInterfaz {

	CultivoModelo registrarCultivo(CultivoModelo cultivo, String correoUsuario) throws Exception;

	public void cultivoModificar(CultivoModelo cultivo, String correoUsuario) throws Exception;
	
	public void cultivoEliminar(Long cultivoId, String correoUsuario) throws Exception;

}
