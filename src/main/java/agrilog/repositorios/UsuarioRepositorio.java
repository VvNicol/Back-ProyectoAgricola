package agrilog.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import agrilog.modelos.UsuarioModelo;

@Repository
public interface UsuarioRepositorio extends JpaRepository<UsuarioModelo, Long> {

    boolean existsByCorreo(String correo);

	UsuarioModelo findByToken(String token);

	UsuarioModelo findByCorreo(String correo); 
}
