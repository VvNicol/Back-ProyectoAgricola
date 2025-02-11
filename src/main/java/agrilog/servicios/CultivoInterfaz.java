package agrilog.servicios;

import agrilog.modelos.CultivoModelo;

public interface CultivoInterfaz {

	CultivoModelo registrarCultivo(CultivoModelo cultivo, String correoUsuario) throws Exception;

}
