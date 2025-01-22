package agrilog.modelos;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "notificaciones", schema = "agrilog")
public class NotificacionModelo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notificacion_id")
	private Long notificacionId; // Identificador de la notificación

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cultivo_id", nullable = false) // Relación con CultivoModelo
	private CultivoModelo cultivoId; // Relación con CultivoModelo

	@Column(nullable = false, length = 255)
	private String mensaje; // Mensaje de la notificación

	@Column(nullable = false)
	private int cantidad; // Cantidad asociada a la notificación

	@Column(name = "fecha_mensaje", nullable = false)
	private Date fechaMensaje; // Fecha de envío del mensaje

	// Constructor vacío requerido por JPA
	public NotificacionModelo() {
	}

	// Constructor con parámetros
	public NotificacionModelo(Long cultivoId, String mensaje, int cantidad, Date fechaMensaje) {
		this.cultivoId = new CultivoModelo(); // Asociar cultivo por su ID
		this.cultivoId.setCultivoId(cultivoId); // Asignar el ID del cultivo
		this.mensaje = mensaje;
		this.cantidad = cantidad;
		this.fechaMensaje = fechaMensaje;
	}

}
