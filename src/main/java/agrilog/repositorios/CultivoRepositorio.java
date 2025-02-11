package agrilog.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import agrilog.modelos.CultivoModelo;

public interface CultivoRepositorio extends JpaRepository<CultivoModelo, Long> {
	
	

}
