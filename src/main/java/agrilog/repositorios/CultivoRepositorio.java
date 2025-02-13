package agrilog.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import agrilog.modelos.CultivoModelo;

@Repository
public interface CultivoRepositorio extends JpaRepository<CultivoModelo, Long> {

	Optional<CultivoModelo> findById(Long id);

	 List<CultivoModelo> findByParcelaId(Long parcelaId);

}
