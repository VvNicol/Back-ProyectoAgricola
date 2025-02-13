package agrilog.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import agrilog.modelos.ParcelaModelo;
import agrilog.modelos.UsuarioModelo;

public interface ParcelaRepositorio extends JpaRepository<ParcelaModelo, Long> {

	ParcelaModelo findByNombreAndUsuarioId(String nombre, UsuarioModelo usuario);

	List<ParcelaModelo> findByUsuario(UsuarioModelo usuario);

}
