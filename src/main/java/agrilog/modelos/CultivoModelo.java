package agrilog.modelos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cultivos", schema = "agrilog")
public class CultivoModelo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cultivo_id")
	private Long cultivoId; // Identificador del cultivo

	@ManyToOne
	@JoinColumn(name = "parcela_id", nullable = false) // Relación con ParcelaModelo
	private ParcelaModelo parcelaId; // Relación con ParcelaModelo

	@Column(nullable = false, length = 100)
	private String nombre; // Nombre del cultivo

	@Column(nullable = true, length = 255)
	private String descripcion; // Descripción del cultivo

	@Column(name = "fecha_vencimiento", nullable = false)
	private LocalDate fechaVencimiento; // Fecha de vencimiento del cultivo

	@Column(name = "fecha_registro", nullable = false)
	private LocalDateTime fechaRegistro; // Fecha de registro del cultivo

	@OneToMany(mappedBy = "cultivoId") // Relación de un cultivo a muchas notificaciones
	private List<NotificacionModelo> notificaciones;

	// Constructores

	public CultivoModelo() {
	}

	// Constructor con parámetros
	public CultivoModelo(Long parcelaId, String nombre, String descripcion, LocalDate fechaVencimiento,
			LocalDateTime fechaRegistro) {
		this.parcelaId = new ParcelaModelo(); // Asociar parcela por su ID
		this.parcelaId.setParcelaId(parcelaId); // Asignar el ID de la parcela
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fechaVencimiento = fechaVencimiento;
		this.fechaRegistro = fechaRegistro;
	}
	
	//Getters y Setters

	/**
	 * @return the cultivoId
	 */
	public Long getCultivoId() {
		return cultivoId;
	}

	/**
	 * @param cultivoId the cultivoId to set
	 */
	public void setCultivoId(Long cultivoId) {
		this.cultivoId = cultivoId;
	}

	/**
	 * @return the parcelaId
	 */
	public ParcelaModelo getParcelaId() {
		return parcelaId;
	}

	/**
	 * @param parcelaId the parcelaId to set
	 */
	public void setParcelaId(ParcelaModelo parcelaId) {
		this.parcelaId = parcelaId;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the fechaVencimiento
	 */
	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * @param fechaVencimiento the fechaVencimiento to set
	 */
	public void setFechaVencimiento(LocalDate fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * @return the fechaRegistro
	 */
	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}

	/**
	 * @param fechaRegistro the fechaRegistro to set
	 */
	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

}
