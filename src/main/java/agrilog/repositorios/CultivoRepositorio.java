package agrilog.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import agrilog.modelos.CultivoModelo;

@Repository
public interface CultivoRepositorio extends JpaRepository<CultivoModelo, Long> {

	Optional<CultivoModelo> findById(Long id);

	/*@Query("SELECT c FROM CultivoModelo c WHERE c.parcelaId.usuarioId = :usuarioId")
    List<CultivoModelo> findAllByUsuarioId(UsuarioModelo usuarioId);;*/


}
