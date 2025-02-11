package agrilog.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import agrilog.modelos.NotificacionModelo;

public interface NotificacionRepositorio extends JpaRepository<NotificacionModelo, Long> {
		
}
