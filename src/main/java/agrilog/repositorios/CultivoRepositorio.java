package agrilog.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import agrilog.dto.CultivoDto;
import agrilog.modelos.CultivoModelo;
import agrilog.modelos.UsuarioModelo;

@Repository
public interface CultivoRepositorio extends JpaRepository<CultivoModelo, Long> {

	Optional<CultivoModelo> findById(Long id);

	@Query("SELECT new agrilog.dto.CultivoDto(c.cultivoId, c.nombre, c.cantidad, c.descripcion, c.fechaSiembra, c.fechaRegistro, p.nombre) " +
	           "FROM CultivoModelo c JOIN c.parcelaId p " +
	           "WHERE p.usuarioId = :usuario")
	    List<CultivoDto> findAllDtoByUsuario(@Param("usuario") UsuarioModelo usuario);

}
